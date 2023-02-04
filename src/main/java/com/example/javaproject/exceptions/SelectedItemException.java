package com.example.javaproject.exceptions;

public class SelectedItemException extends RuntimeException {
    public SelectedItemException() {
    }

    public SelectedItemException(String message) {
        super(message);
    }

    public SelectedItemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SelectedItemException(Throwable cause) {
        super(cause);
    }

    public SelectedItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
