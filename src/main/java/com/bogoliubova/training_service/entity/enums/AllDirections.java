package com.bogoliubova.training_service.entity.enums;

public enum AllDirections {
    MATHEMATICS("mathematics"),
    ENGLISH("english"),
    GERMAN("german"),
    BIOLOGY("biology"),
    CHEMISTRY("chemistry"),
    PHYSICS("physics"),
    INFORMATICS("informatics"),
    ECONOMICS("economics"),
    MANAGEMENT("management"),
    BOOKKEEPING("bookkeeping"),
    LOGISTICS("logistics"),
    MEMORY_DEVELOPMENT("memory_development"),
    READING("reading"),
    LOGIC("logic"),
    MANUAL_WORK("manual_work");
    private final String value;

    AllDirections(String value) {
        this.value = value;
    }

}
