package main.java.com.education.exceptions.inout.incorrectInput;

import main.java.com.education.exceptions.inout.InOutException;

/**
 * Common class for exceptions of parsing command.
 */
public class IncorrectInputException extends InOutException {
    public IncorrectInputException() {
        super();
    }

    public IncorrectInputException(String message) {
        super(message);
    }
}