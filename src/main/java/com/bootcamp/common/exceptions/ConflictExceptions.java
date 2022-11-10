package com.bootcamp.common.exceptions;

public class ConflictExceptions extends  Exception {

    public ConflictExceptions() {
    }

    public ConflictExceptions(String message) {
        super(message);
    }

    public ConflictExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictExceptions(Throwable cause) {
        super(cause);
    }

    public ConflictExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
