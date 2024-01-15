package com.bogoliubova.training_service.entity.enums;

public enum AllServices {

    PREPARATION_FOR_SCHOOL("preparation_for_school"),
    SCHOOL_PROGRAM("school_program"),
    PREPARATION_FOR_TECHNICAL_SCHOOL("preparation_for_technical_school"),
    PREPARATION_FOR_UNIVERSITY("preparation_for_university"),
    PLAYING_MUSICAL_INSTRUMENTS("playing_musical_instruments"),
    LEARNING_A_CRAFT("learning_a_craft"),
    TRAINING_FOR_ADULTS("training_for_adults"),
    TRAINING_FOR_PENSIONEERS("training_for_pensioneers"),
    BOOKSTORE("bookstore");

    private final String value;

    AllServices(String value) {
        this.value = value;
    }
}
