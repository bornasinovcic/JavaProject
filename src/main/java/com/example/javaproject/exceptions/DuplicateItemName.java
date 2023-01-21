package com.example.javaproject.exceptions;

public class DuplicateItemName extends Exception {
    public DuplicateItemName() {
    }

    public DuplicateItemName(String message) {
        super(message);
    }

    public DuplicateItemName(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItemName(Throwable cause) {
        super(cause);
    }

    public DuplicateItemName(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
