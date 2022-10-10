package ru.netology.sql6.entity.enums;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender getGender(String gender) {
        switch (gender) {
            case "MALE":
                return MALE;
            case "FEMALE":
                return FEMALE;
            default:
                return null;
        }
    }

}
