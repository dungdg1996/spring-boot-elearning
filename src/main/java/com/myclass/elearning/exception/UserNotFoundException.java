package com.myclass.elearning.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String mess) {
        super(mess);
    }
}
