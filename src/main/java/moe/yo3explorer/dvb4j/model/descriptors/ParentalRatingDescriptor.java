package moe.yo3explorer.dvb4j.model.descriptors;

import moe.yo3explorer.dvb4j.model.Descriptor;
import moe.yo3explorer.dvb4j.model.ParentalRating;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ParentalRatingDescriptor implements Descriptor {
    @Override
    public int getTag() {
        return 0x55;
    }

    @Override
    public void readFrom(@NotNull byte[] buffer) {
        ratings = new ParentalRating[buffer.length / 4];

        for (int i = 0; i < buffer.length; i += 4)
        {
            String countryCode = new String(buffer,i * 4,3, StandardCharsets.US_ASCII);

            Integer rating = null;
            int ratingRaw = buffer[(i * 4) + 3];
            if (ratingRaw >= 1 && ratingRaw <= 0x0f)
                rating = ratingRaw + 3;

            ratings[i / 4] = new ParentalRating(countryCode,rating);
        }
    }

    private ParentalRating[] ratings;

    public ParentalRating[] getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "ParentalRatingDescriptor{" +
                "ratings=" + Arrays.toString(ratings) +
                '}';
    }
}
