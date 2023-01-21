package com.example.javaproject.exceptions;

public class DuplicateItemId extends Exception {
    public DuplicateItemId() {
    }

    public DuplicateItemId(String message) {
        super(message);
    }

    public DuplicateItemId(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemId(Throwable cause) {
        super(cause);
    }

    public DuplicateItemId(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
