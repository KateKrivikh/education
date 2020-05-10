package com.education.exceptions.inout.incorrectInput;

import com.education.exceptions.inout.InOutException;

/**
 * Common class for exceptions of parsing command.
 */
public class IncorrectInputException extends InOutException {
    public IncorrectInputException(String message) {
        super(message);
    }

    public IncorrectInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
