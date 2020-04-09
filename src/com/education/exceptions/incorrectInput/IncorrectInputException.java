package com.education.exceptions.incorrectInput;

import com.education.exceptions.InOutException;

/**
 * Common class for exceptions of parsing command.
 * TODO Should exceptions be checked here?..
 */
public class IncorrectInputException extends InOutException {
    public IncorrectInputException(String message) {
        super(message);
    }
}
