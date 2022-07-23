package com.community.soccer.exception;

public class PageRequestException extends RuntimeException {
    public PageRequestException() {
        super();
    }

    public PageRequestException(String message) {
        super(message);
    }

    public PageRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
