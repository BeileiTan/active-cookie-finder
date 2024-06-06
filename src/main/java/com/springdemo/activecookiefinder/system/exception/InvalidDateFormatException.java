package com.springdemo.activecookiefinder.system.exception;

public class InvalidDateFormatException extends RuntimeException {
    public InvalidDateFormatException(String message) {
        super(message);
    }

    public InvalidDateFormatException(String date, Throwable cause) {
        super("The date format " + date + " is invalid, Please use yyyy-MM-dd format.", cause);
    }
}