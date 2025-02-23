package com.calendarapp.exception;

public class UserWithoutGroupException extends RuntimeException {
    public UserWithoutGroupException(String message) {
        super(message);
    }
}
