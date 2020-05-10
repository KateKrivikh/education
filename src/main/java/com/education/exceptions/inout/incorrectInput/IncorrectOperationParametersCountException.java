package com.education.exceptions.inout.incorrectInput;

public class IncorrectOperationParametersCountException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT =
            "Неверное количество параметров!\nОжидаемое количество параметров: %d";

    private IncorrectOperationParametersCountException(String message) {
        super(message);
    }

    public IncorrectOperationParametersCountException(int parametersCount) {
        this(String.format(MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT, parametersCount));
    }
}
