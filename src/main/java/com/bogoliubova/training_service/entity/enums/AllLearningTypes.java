package com.bogoliubova.training_service.entity.enums;

public enum AllLearningTypes {
    ONLINE("online"),
    OFFLINE("offline"),
    GROUP("group"),
    INDIVIDUAL("individual"),
    REGULAR("regular"),
    TIMELY("timely");

    private final String value;

    AllLearningTypes(String value) {
        this.value = value;
    }
}
