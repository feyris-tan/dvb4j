package moe.yo3explorer.dvb4j;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class PsiSection
{
    public boolean headerComplete;
    public int i_need;
    private ByteArrayOutputStream data;

    public PsiSection()
    {
    }

    public void appendData(byte[] appendable)
    {
        if (data == null) {
            data = new ByteArrayOutputStream();
        }
        data.write(appendable,0,appendable.length);
    }

    public byte[] getData()
    {
        return data.toByteArray();
    }

    public int getTableId()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int tableId = ((int)data[0]) & 0xff;
        return tableId;
    }

    public int getSyntaxIndicator()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int syntaxIndicator = data[1] & 0x80;
        return syntaxIndicator;
    }

    public int getPrivateIndicator()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int privateIndicator = data[1] & 0x40;
        return privateIndicator;
    }

    public boolean validate()
    {
        if (!headerComplete)
            return false;

        boolean validCrc32 = false;
        boolean hasCrc32 = hasCrc32();

        if (hasCrc32)
            validCrc32 = DvbCrc32.validatePsi(this);

        return !hasCrc32 || validCrc32;
    }

    private boolean hasCrc32()
    {
        int tableId = getTableId();
        if (tableId == 0x70 || tableId == 0x71 || tableId == 0x72 || tableId == 0x7E)
            return false;

        return (getSyntaxIndicator() != 0) || tableId == 0x73;
    }

    public int getLastNumber()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int syntaxIndicator = data[1] & 0x80;
        if (syntaxIndicator != 0)
        {
            int result = (data[7]) & 0xff;
            return result;
        }
        else
            return 0;
    }

    public int getNumber()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int syntaxIndicator = data[1] & 0x80;
        if (syntaxIndicator != 0)
        {
            int result = (data[6]) & 0xff;
            return result;
        }
        else
            return 0;
    }

    public int getCurrentNext()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int syntaxIndicator = data[1] & 0x80;
        if (syntaxIndicator != 0)
        {
            int result = (data[5] & 0x1);
            return result;
        }
        else
            return 0;
    }

    public int getVersion()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int syntaxIndicator = data[1] & 0x80;
        if (syntaxIndicator != 0)
        {
            int result = (data[5] & 0x3e);
            result >>= 1;
            return result;
        }
        else
            return 0;
    }

    public int getExtension()
    {
        if (!headerComplete)
            throw new DvbException("Header is not yet complete!");

        byte[] data = getData();
        int syntaxIndicator = data[1] & 0x80;
        if (syntaxIndicator != 0)
        {
            int result = (data[3]) & 0xff;
            result <<= 8;
            result |= data[4];
            return result;
        }
        else
            return 0;
    }

    public boolean check(int otherTableId)
    {
        int thisTableId = getTableId();
        if (thisTableId != otherTableId)
            return false;

        if ((getSyntaxIndicator() == 0) && (otherTableId != 0x70) && (otherTableId != 0x73))
            return false;

        return true;
    }

    public int getPayloadStart()
    {
        if (getTableId() == 0x70)
            return 3;
        return 8;
    }

    public int getPayloadEnd()
    {
        boolean hasCrc32 = hasCrc32();
        int endResult = data.size();
        if (hasCrc32)
            endResult -= 4;
        return endResult;
    }

    public ByteBuffer getPayload()
    {
        byte[] bytes = Arrays.copyOfRange(getData(), getPayloadStart(), getPayloadEnd());
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return byteBuffer;
    }
}
