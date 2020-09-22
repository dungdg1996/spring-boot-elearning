package com.myclass.elearning.exception;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException() {
        super("Video not found");
    }
}
