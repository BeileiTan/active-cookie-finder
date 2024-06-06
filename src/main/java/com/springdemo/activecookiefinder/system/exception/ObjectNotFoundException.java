package com.springdemo.activecookiefinder.system.exception;

import java.time.LocalDate;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, String object) {
        super("Could not find the " + objectName + ": " + object);
    }

}
