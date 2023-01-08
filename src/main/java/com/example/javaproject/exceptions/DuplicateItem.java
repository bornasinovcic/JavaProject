package com.example.javaproject.exceptions;

public class DuplicateItem extends Exception {
    public DuplicateItem() {
    }

    public DuplicateItem(String message) {
        super(message);
    }

    public DuplicateItem(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateItem(Throwable cause) {
        super(cause);
    }

    public DuplicateItem(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
