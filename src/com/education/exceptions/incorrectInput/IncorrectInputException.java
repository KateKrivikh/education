package com.education.exceptions.incorrectInput;

import com.education.exceptions.InOutException;

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
