package moe.yo3explorer.dvb4j;

import moe.yo3explorer.dvb4j.decoders.*;
import moe.yo3explorer.dvb4j.model.PATEntry;
import org.jetbrains.annotations.NotNull;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class DvbContext
{
    public void pushPacket(DvbPacket dvbPacket)
    {
        if (this.dvbReceiver == null)
        {
            throw new DvbException("A receiver must be set before processing packets!");
        }

        packetNumber++;
        if (pids == null) {
            pids = new DvbPacket[8192];
            psiSections = new PsiSection[8192];
            interestingPids = new boolean[8192];
            interestingPids[0] = true;      //PAT
            interestingPids[1] = true;      //CAT
            interestingPids[0x11] = true;   //SDT & BAT
            interestingPids[0x12] = true;   //EIT
            interestingPids[0x14] = true;   //TDT
            attachPsiDecoder(new PATDecoder(this));
            attachPsiDecoder(new TDTDecoder(this.dvbReceiver));
            attachPsiDecoder(new CATDecoder(this.dvbReceiver));
            attachPsiDecoder(new SDTDecoder(this.dvbReceiver));
            attachPsiDecoder(new TOTDecoder(this.dvbReceiver));
            attachPsiDecoder(new BATDecoder(this.dvbReceiver));
            attachPsiDecoder(new NITDecoder(this.dvbReceiver));
            attachPsiDecoder(new EITDecoder(this.dvbReceiver));
            attachPsiDecoder(new STDecoder());
        }

        int pid = dvbPacket.getPid();
        if (pids[pid] == null)
        {
            pids[pid] = dvbPacket;
        }
        else
        {
            int oldContinuity = pids[pid].getContinuity();
            int newContinuity = dvbPacket.getContinuity();
            if (newContinuity == (oldContinuity + 1) || (oldContinuity == 15 && newContinuity == 0) || pid == 8191 || !dvbPacket.isPayloadFlagSet() || !interestingPids[pid])
            {
                pids[pid] = dvbPacket;
            }
            else
            {
                pids[pid] = null;
                psiSections[pid] = null;
                packetLossEvents++;
                dvbReceiver.onPacketLoss(pid,oldContinuity +1, newContinuity);
                return;
            }
        }

        if (!dvbPacket.isPayloadFlagSet())
            return;

        if (!interestingPids[pid])
            return;

        boolean psiJustStarted = false;
        if (psiSections[pid] == null)
        {
            if (dvbPacket.isPayloadUnitStart()) {
                psiSections[pid] = new PsiSection();
                psiSections[pid].i_need = 3;
                psiSections[pid].headerComplete = false;
                psiJustStarted = true;
            }
            else
            {
                return;
            }
        }

        PsiSection psiSection = psiSections[pid];
        ByteBuffer payload = dvbPacket.getPayload();
        int available = payload.limit();
        if (psiJustStarted)
        {
            int startOffset = dvbPacket.getPayloadStartOffset();
            if (startOffset > 0) {
                startOffset = payload.position() + startOffset;
                if (startOffset > payload.limit())
                {
                    System.out.println("got broken packet!");
                    return;
                }
                payload.position(payload.position() + startOffset);
                available -= startOffset;
            }
        }
        while (available > 0)
        {
            if (available > psiSection.i_need)
            {
                //Noch genug im Buffer, um i_need auf 0 zu kriegen.
                byte[] temp1 = new byte[psiSection.i_need];
                payload.get(temp1);
                available -= psiSection.i_need;
                psiSection.appendData(temp1);

                if (!psiSection.headerComplete)
                {
                    psiSection.headerComplete = true;
                    short sectionLength = ByteBuffer.wrap(psiSection.getData()).getShort(1);
                    sectionLength &= 0x0fff;
                    psiSection.i_need = sectionLength;
                }
                else
                {
                    if (psiSection.validate()) {
                        synchronized (this) {
                            currentlyProcessingPid = pid;
                            gather(psiSection);
                        }
                    }
                    psiSection = null;
                    psiSections[pid] = null;

                    boolean newSection = false;
                    if (available > 0) {
                        byte payloadPos = payload.get();
                        payload.position(payload.position() - 1);
                        if (payloadPos != (byte)0xff)
                            newSection = true;
                    }

                    if (newSection) {
                        psiSections[pid] = new PsiSection();
                        psiSection = psiSections[pid];
                        psiSection.i_need = 3;
                        psiSection.headerComplete = false;
                    }
                    else
                    {
                        available = 0;
                    }
                }
            }
            else
            {
                //Nicht mehr genug da...
                byte[] filler = new byte[available];
                payload.get(filler);
                psiSection.appendData(filler);
                psiSection.i_need -= available;
                available = 0;
            }
        }
    }

    public void pushPacket(byte[] rawBytes)
    {
        DvbPacket dvbPacket = new DvbPacket(rawBytes);
        pushPacket(dvbPacket);
    }

    public int[] listAvailablePids()
    {
        if (pids == null)
            return new int[0];

        int[] result = Arrays.stream(pids).filter(x -> x != null).mapToInt(x -> x.getPid()).toArray();
        return result;
    }

    private void gather(@NotNull PsiSection psiSection)
    {
        int table = psiSection.getTableId();
        if (psiDecoders[table] != null)
        {
            psiDecoders[table].handlePsi(psiSection);
            return;
        }
        System.out.printf("Gathering for table %02X\n",table);
    }

    public void attachPsiDecoder(PSIDecoder psiDecoder)
    {
        if (psiDecoders == null)
            psiDecoders = new PSIDecoder[255];

        int[] tableIds = psiDecoder.getTableIds();
        for (int tableId : tableIds) {
            if (tableId > psiDecoders.length)
                throw new DvbException(String.format("This PSI Decoder is broken. The maximum tableId is 255, however it is presented as %d", tableId));

            if (psiDecoders[tableId] != null)
                throw new DvbException(String.format("There is already a decoder attached for table %d", tableId));

            psiDecoders[tableId] = psiDecoder;
        }
    }

    public void attachPatEntry(@NotNull PATEntry patEntry)
    {
        int pmtPid = patEntry.getPid();
        if (!interestingPids[pmtPid])
        {
            interestingPids[pmtPid] = true;

            if (psiDecoders[2] == null)
                psiDecoders[2] = new PMTDecoder(this);

            if (dvbReceiver != null)
                dvbReceiver.onNewPatEntry(patEntry);
        }
    }

    public DvbReceiver getDvbReceiver() {
        return dvbReceiver;
    }

    public void setDvbReceiver(DvbReceiver dvbReceiver) {
        this.dvbReceiver = dvbReceiver;
    }

    public int getLastProcessedPid() {
        return currentlyProcessingPid;
    }

    private DvbPacket[] pids;
    private PsiSection[] psiSections;
    private boolean[] interestingPids;
    private long packetNumber;
    private PSIDecoder[] psiDecoders;
    private DvbReceiver dvbReceiver;
    private int currentlyProcessingPid;
    private long packetLossEvents;

    public long getNumPacketLossEvents() {
        return packetLossEvents;
    }
}
