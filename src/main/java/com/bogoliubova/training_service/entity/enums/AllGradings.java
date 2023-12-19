package com.bogoliubova.training_service.entity.enums;

public enum AllGradings {
    KINDERGARTEN("kindergarten"),
    JUNIOR_SCHOOL("junior_school"),
    MIDDLE_SCHOOL("middle_school"),
    OLD_SCHOOL("old_school"),
    STUDENTS("students"),
    ADULTS("adults"),
    PENSIONERS("pensioners");

    private final String value;

    AllGradings(String value) {
        this.value = value;
    }
}
