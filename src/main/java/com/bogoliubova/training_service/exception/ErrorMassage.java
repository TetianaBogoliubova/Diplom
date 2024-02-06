package com.bogoliubova.training_service.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMassage {
    public static final String M_CUSTOMER_NOT_FOUND = "Customer id is not found";
    public static final String M_CUSTOMER_NOT_UPDATE = "Customer data not update";
    public static final String M_CUSTOMER_FIELD_MISSING = "Customer field is missing";
    public static final String M_LOCATION_NOT_FOUND = "Location id is not found";
    public static final String M_TEACHER_NOT_FOUND = "Teacher id is not found";

    public static final String M_TEACHER_IN_THIS_CITY_NOT_FOUND = "Teacher in this city not found";
}
