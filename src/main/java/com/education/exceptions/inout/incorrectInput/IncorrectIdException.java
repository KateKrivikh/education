package com.education.exceptions.inout.incorrectInput;

public class IncorrectIdException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_ID =
            "Не удалось определить идентификатор человека по параметру %s\nОжидаемый формат - целое положительное число";

    public IncorrectIdException(String idString) {
        super(String.format(MESSAGE_PARSE_EXCEPTION_ID, idString));
    }

}
