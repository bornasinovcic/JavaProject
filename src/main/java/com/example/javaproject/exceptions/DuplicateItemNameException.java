package com.example.javaproject.exceptions;

public class DuplicateItemNameException extends Exception {
    public DuplicateItemNameException() {
    }

    public DuplicateItemNameException(String message) {
        super(message);
    }

    public DuplicateItemNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemNameException(Throwable cause) {
        super(cause);
    }

    public DuplicateItemNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
