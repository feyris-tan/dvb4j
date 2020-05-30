package moe.yo3explorer.dvb4j.model.enums;

import moe.yo3explorer.dvb4j.DvbException;

public enum ContentAssignment
{
    UNDEFIEND_CONTENT, MOVIE, MOVIE_DETECTIVE, MOVIE_ADVENTURE, MOVIE_SCIENCE_FICTION, MOVIE_COMEDY, MOVIE_MELODRAMATIC,
    MOVIE_ROMANCE, MOVIE_SERIOUS, MOVIE_ADULT_DRAMA, RESERVED, NEWS, WEATHER_REPORT, NEWS_MAGAZINE, DOCUMENTARY,
    NEWS_DISCUSSION, USER_DEFINED, SHOW, GAME_SHOW, VARIETY_SHOW, TALK_SHOW, SPORTS_OTHER, SPORTS_SPECIAL_EVENT,
    SPORTS_MAGAZINE, FOOTBALL, TENNIS, OTHER_TEAM_SPORT, ATHLETICS, MOTOR_SPORT, WATER_SPORTS, WINTER_SPORTS,
    /**
     * e.g. horse racing
     */
    EQUESTRIAN,
    MARTIAL_ARTS, CHILDREN, CHILDREN_PRESCHOOL, CHILDREN_6_TO_14, CHILDREN_10_TO_16, SCHOOL_PROGRAMMES,
    CHILDREN_CARTOONS, MUSIC, ROCK_MUSIC, CLASSICAL_MUSIC, FOLK_MUSIC, JAZZ_MUSIC, MUSICAL, BALLET, ARTS_AND_CULTURE,
    PERFORMING_ARTS, FINE_ARTS, RELIGION, POP_CULTURE, LITERATURE, ARTS_FILM, EXPERIMENTAL_FILM, PRESS, NEW_MEDIA,
    CULTURE_MAGAZINE, FASHION, SOCIAL_AND_POLITICAL_ISSUES, SOCIAL_AND_POLITICAL_ISSUES_MAGAZINE, ECONOMICS,
    REMARKABLE_PEOPLE, EDUCATION_SCIENCE_FACTUAL_TOPICS, NATURE, TECHNOLOGY, MEDICINE, FOREIGN_COUNTRIES,
    SOCIAL_AND_SPIRITUAL_SCIENCES, FURTHER_EDUCATION, LANGUAGES, LEISURE_HOBBIES, TOURISM, HANDICRAFT, HOBBY_MOTORING,
    FITNESS_AND_HEALTH, COOKING, SHOPPING, GARDENING, ORIGINAL_LANGUAGE, BLACK_AND_WHITE, UNPUBLISHED, LIVE_BROADCAST,
    _3D, REGIONAL, ADULT;

    public static ContentAssignment decodeContentAssignment(byte theByte)
    {
        int level1 = (theByte & 0xff) >> 4;
        int level2 = theByte & 0x0f;

        if (level1 == 0x00)
            return UNDEFIEND_CONTENT;
        if (level1 == 0x01) {
            if (level2 == 0x00)
                return MOVIE;
            else if (level2 == 0x01)
                return MOVIE_DETECTIVE;
            else if (level2 == 0x02)
                return MOVIE_ADVENTURE;
            else if (level2 == 0x03)
                return MOVIE_SCIENCE_FICTION;
            else if (level2 == 0x04)
                return MOVIE_COMEDY;
            else if (level2 == 0x05)
                return MOVIE_MELODRAMATIC;
            else if (level2 == 0x06)
                return MOVIE_ROMANCE;
            else if (level2 == 0x07)
                return MOVIE_SERIOUS;
            else if (level2 == 0x08)
                return MOVIE_ADULT_DRAMA;
            else if (level2 >= 0x09 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x02)
        {
            if (level2 == 0x0)
                return NEWS;
            else if (level2 == 0x1)
                return WEATHER_REPORT;
            else if (level2 == 0x2)
                return NEWS_MAGAZINE;
            else if (level2 == 0x3)
                return DOCUMENTARY;
            else if (level2 == 0x4)
                return NEWS_DISCUSSION;
            else if (level2 >= 0x05 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x03)
        {
            if (level2 == 0x00)
                return SHOW;
            else if (level2 == 0x01)
                return GAME_SHOW;
            else if (level2 == 0x02)
                return VARIETY_SHOW;
            else if (level2 == 0x03)
                return TALK_SHOW;
            else if (level2 >= 0x04 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x04)
        {
            if (level2 == 0x00)
                return SPORTS_OTHER;
            else if (level2 == 0x01)
                return SPORTS_SPECIAL_EVENT;
            else if (level2 == 0x02)
                return SPORTS_MAGAZINE;
            else if (level2 == 0x03)
                return FOOTBALL;
            else if (level2 == 0x04)
                return TENNIS;
            else if (level2 == 0x05)
                return OTHER_TEAM_SPORT;
            else if (level2 == 0x06)
                return ATHLETICS;
            else if (level2 == 0x07)
                return MOTOR_SPORT;
            else if (level2 == 0x08)
                return WATER_SPORTS;
            else if (level2 == 0x09)
                return WINTER_SPORTS;
            else if (level2 == 0x0A)
                return EQUESTRIAN;
            else if (level2 == 0x0B)
                return MARTIAL_ARTS;
            else if (level2 >= 0x0C && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if(level1 == 0x05)
        {
            if (level2 == 0x00)
                return CHILDREN;
            else if (level2 == 0x01)
                return CHILDREN_PRESCHOOL;
            else if (level2 == 0x02)
                return CHILDREN_6_TO_14;
            else if (level2 == 0x03)
                return CHILDREN_10_TO_16;
            else if (level2 == 0x04)
                return SCHOOL_PROGRAMMES;
            else if (level2 == 0x05)
                return CHILDREN_CARTOONS;
            else if (level2 >= 0x06 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x06)
        {
            if (level2 == 0x00)
                return MUSIC;
            else if (level2 == 0x01)
                return ROCK_MUSIC;
            else if (level2 == 0x02)
                return CLASSICAL_MUSIC;
            else if (level2 == 0x03)
                return FOLK_MUSIC;
            else if (level2 == 0x04)
                return JAZZ_MUSIC;
            else if (level2 == 0x05)
                return MUSICAL;
            else if (level2 == 0x06)
                return BALLET;
            else if (level2 >= 0x07 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x07)
        {
            if (level2 == 0x00)
                return ARTS_AND_CULTURE;
            else if (level2 == 0x01)
                return PERFORMING_ARTS;
            else if (level2 == 0x02)
                return FINE_ARTS;
            else if (level2 == 0x03)
                return RELIGION;
            else if (level2 == 0x04)
                return POP_CULTURE;
            else if (level2 == 0x05)
                return LITERATURE;
            else if (level2 == 0x06)
                return ARTS_FILM;
            else if (level2 == 0x07)
                return EXPERIMENTAL_FILM;
            else if (level2 == 0x08)
                return PRESS;
            else if (level2 == 0x09)
                return NEW_MEDIA;
            else if (level2 == 0x0A)
                return CULTURE_MAGAZINE;
            else if (level2 == 0x0B)
                return FASHION;
            else if (level2 >= 0x0C && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x08)
        {
            if (level2 == 0x00)
                return SOCIAL_AND_POLITICAL_ISSUES;
            else if (level2 == 0x01)
                return SOCIAL_AND_POLITICAL_ISSUES_MAGAZINE;
            else if (level2 == 0x02)
                return ECONOMICS;
            else if (level2 == 0x03)
                return REMARKABLE_PEOPLE;
            else if (level2 >= 0x04 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x09)
        {
            if (level2 == 0x00)
                return EDUCATION_SCIENCE_FACTUAL_TOPICS;
            else if (level2 == 0x01)
                return NATURE;
            else if (level2 == 0x02)
                return TECHNOLOGY;
            else if (level2 == 0x03)
                return MEDICINE;
            else if (level2 == 0x04)
                return FOREIGN_COUNTRIES;
            else if (level2 == 0x05)
                return SOCIAL_AND_SPIRITUAL_SCIENCES;
            else if (level2 == 0x06)
                return FURTHER_EDUCATION;
            else if (level2 == 0x07)
                return LANGUAGES;
            else if (level2 >= 0x08 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x0A)
        {
            if (level2 == 0x00)
                return LEISURE_HOBBIES;
            else if (level2 == 0x01)
                return TOURISM;
            else if (level2 == 0x02)
                return HANDICRAFT;
            else if (level2 == 0x03)
                return HOBBY_MOTORING;
            else if (level2 == 0x04)
                return FITNESS_AND_HEALTH;
            else if (level2 == 0x05)
                return COOKING;
            else if (level2 == 0x06)
                return SHOPPING;
            else if (level2 == 0x07)
                return GARDENING;
            else if (level2 >= 0x08 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x0B)
        {
            if (level2 == 0x00)
                return ORIGINAL_LANGUAGE;
            else if (level2 == 0x01)
                return BLACK_AND_WHITE;
            else if (level2 == 0x02)
                return UNPUBLISHED;
            else if (level2 == 0x03)
                return LIVE_BROADCAST;
            else if (level2 == 0x04)
                return _3D;
            else if (level2 == 0x05)
                return REGIONAL;
            else if (level2 >= 0x06 && level2 <= 0x0E)
                return RESERVED;
            else if (level2 == 0x0F)
                return USER_DEFINED;
        }
        else if (level1 == 0x0c)
        {
            if (level2 == 0x00)
                return ADULT;
            else if (level2 >= 0x01 && level2 <= 0x0e)
                return RESERVED;
            else if (level2 == 0x0f)
                return USER_DEFINED;
        }
        else if (level1 == 0x0d || level1 == 0x0e)
            return RESERVED;
        else if (level1 == 0x0f)
            return USER_DEFINED;

        throw new DvbException("This content assignment is not yet implemented!");
    }
}
