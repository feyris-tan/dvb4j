package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.DvbTimeConverter;
import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.descriptorEntities.LocalTimeOffset;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LocalTimeOffsetDescriptor implements Descriptor {
    public LocalTimeOffsetDescriptor()
    {
        localTimeOffsets = new ArrayList<>();
    }

    @Override
    public int getTag() {
        return 0x58;
    }

    @Override
    public void readFrom(byte[] buffer) {
        ByteBuffer wrap = ByteBuffer.wrap(buffer);

        for (; wrap.limit() > wrap.position();)
        {
            byte[] countryCodeBuffer = new byte[3];
            wrap.get(countryCodeBuffer);
            String countryCode = new String(countryCodeBuffer, StandardCharsets.US_ASCII);

            byte regionFlags = wrap.get();
            int countryRegionId = (regionFlags & 0xff) >> 2;

            boolean localTimeOffsetPolariyNegative = (regionFlags & 0x01) != 0;

            Integer localTimeOffset = DvbTimeConverter.timeOffsetInJavaTime(wrap);
            if (localTimeOffset == null)
                return;
            Date timeOfChange = DvbTimeConverter.parseTime(wrap);
            int nextTimeOffset = DvbTimeConverter.timeOffsetInJavaTime(wrap);

            localTimeOffsets.add(new LocalTimeOffset(countryCode,countryRegionId,localTimeOffsetPolariyNegative,localTimeOffset,timeOfChange,nextTimeOffset));
        }
    }

    private List<LocalTimeOffset> localTimeOffsets;

    public List<LocalTimeOffset> getLocalTimeOffsets() {
        return localTimeOffsets;
    }

    @Override
    public String toString() {
        return "LocalTimeOffsetDescriptor{" +
                "localTimeOffsets=" + localTimeOffsets.size() +
                '}';
    }
}
