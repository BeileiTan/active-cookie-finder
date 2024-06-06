package com.springdemo.activecookiefinder.system.exception;

public class InvalidLogFormatException extends RuntimeException{
    public InvalidLogFormatException(String message) {
        super(message);
    }

    public InvalidLogFormatException(String date, Throwable cause) {
        super("The logging date " + date + " is invalid, Please check cookie_log file.", cause);
    }
}
