package com.education.exceptions.inout.incorrectInput;

public class EmptyParameterException extends IncorrectInputException {

    public static final String MESSAGE_PARSE_EXCEPTION_EMPTY_PARAMETER = "Пустой параметр: %s";

    public EmptyParameterException(String parameterName) {
        super(String.format(MESSAGE_PARSE_EXCEPTION_EMPTY_PARAMETER, parameterName));
    }
}
