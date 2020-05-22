package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CountryAvailablityDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x49;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        countries = new ArrayList<>();
        int offset = 0;
        byte flags = buffer[offset++];
        countryAvailability = (flags & 0x80) != 0;
        for (; offset < buffer.length;)
        {
            byte[] countryRaw = new byte[3];
            System.arraycopy(buffer,offset,countryRaw,0,3);
            offset += 3;
            countries.add(new String(countryRaw, StandardCharsets.US_ASCII));
        }
    }

    private ArrayList<String> countries;
    private boolean countryAvailability;

    public ArrayList<String> getCountries() {
        return countries;
    }

    public boolean isCountryAvailability() {
        return countryAvailability;
    }
}
