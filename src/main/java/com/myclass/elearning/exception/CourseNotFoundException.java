package com.myclass.elearning.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException() {
        super("Course not found");
    }
}
