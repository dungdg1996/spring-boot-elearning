package com.myclass.elearning.exception;

public class TargetNotFoundException extends RuntimeException {
    public TargetNotFoundException() {
        super("Target not found");
    }
}
