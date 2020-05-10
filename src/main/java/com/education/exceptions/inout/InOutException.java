package com.education.exceptions.inout;

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

    public InOutException(String message, Throwable cause) {
        super(message, cause);
    }

}
