package com.bootcamp.common.exceptions;

public class TechnicalExceptions extends Exception {
    public TechnicalExceptions() {
    }

    public TechnicalExceptions(String message) {
        super(message);
    }

    public TechnicalExceptions(String message, Throwable cause) {
        super(message, cause);
    }

    public TechnicalExceptions(Throwable cause) {
        super(cause);
    }

    public TechnicalExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
