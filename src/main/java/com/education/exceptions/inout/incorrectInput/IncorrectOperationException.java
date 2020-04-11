package main.java.com.education.exceptions.inout.incorrectInput;

public class IncorrectOperationException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_UNKNOWN_COMMAND =
            "Не удалось определить операцию по параметру: %s";

    public IncorrectOperationException(String operationString) {
        super(String.format(MESSAGE_PARSE_EXCEPTION_UNKNOWN_COMMAND, operationString));
    }
}
