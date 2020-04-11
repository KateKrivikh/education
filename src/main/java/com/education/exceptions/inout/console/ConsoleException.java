package main.java.com.education.exceptions.inout.console;

import main.java.com.education.exceptions.inout.InOutException;

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
