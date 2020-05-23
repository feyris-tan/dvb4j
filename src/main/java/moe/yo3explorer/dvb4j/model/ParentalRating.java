package moe.yo3explorer.dvb4j.model;

public class ParentalRating
{

    private final String countryCode;
    private final Integer rating;

    public ParentalRating(String countryCode, Integer rating) {

        this.countryCode = countryCode;
        this.rating = rating;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Integer getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "ParentalRating{" +
                "countryCode='" + countryCode + '\'' +
                ", rating=" + rating +
                '}';
    }
}
