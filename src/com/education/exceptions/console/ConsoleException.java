package com.education.exceptions.console;

import com.education.exceptions.InOutException;

/**
 * Common class for console exceptions.
 */
public class ConsoleException extends InOutException {
    public ConsoleException() {
    }

    public ConsoleException(String message) {
        super(message);
    }
}
