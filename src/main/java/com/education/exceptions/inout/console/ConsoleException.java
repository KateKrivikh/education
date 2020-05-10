package com.education.exceptions.inout.console;

import com.education.exceptions.inout.InOutException;

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
