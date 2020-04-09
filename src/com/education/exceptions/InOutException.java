package com.education.exceptions;

/**
 * Common class for exceptions of in/out
 */
public class InOutException extends RuntimeException {
    public InOutException() {
        super();
    }

    public InOutException(String message) {
        super(message);
    }
}
