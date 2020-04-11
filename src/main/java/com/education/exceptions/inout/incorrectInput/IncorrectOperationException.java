package main.java.com.education.exceptions.inout.incorrectInput;

public class IncorrectOperationException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_UNKNOWN_COMMAND = "Неизвестная команда: ";

    public IncorrectOperationException(String operationString) {
        super(MESSAGE_PARSE_EXCEPTION_UNKNOWN_COMMAND + operationString);
    }
}
