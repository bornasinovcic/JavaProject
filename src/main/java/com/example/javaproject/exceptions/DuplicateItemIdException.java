package com.example.javaproject.exceptions;

public class DuplicateItemIdException extends Exception {
    public DuplicateItemIdException() {
    }

    public DuplicateItemIdException(String message) {
        super(message);
    }

    public DuplicateItemIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemIdException(Throwable cause) {
        super(cause);
    }

    public DuplicateItemIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
