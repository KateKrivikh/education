package com.education.exceptions.incorrectInput;

public class IncorrectSexException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_SEX =
            "Не удалось определить пол человека по параметру %s\nДостуные форматы: %s";

    private IncorrectSexException(String message) {
        super(message);
    }

    public IncorrectSexException(String sexString, String formats) {
        this(String.format(MESSAGE_PARSE_EXCEPTION_SEX, sexString, formats));
    }

}
