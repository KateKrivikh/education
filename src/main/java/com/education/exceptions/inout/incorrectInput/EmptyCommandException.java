package main.java.com.education.exceptions.inout.incorrectInput;

public class EmptyCommandException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_EMPTY_COMMAND = "Пустая команда!";

    public EmptyCommandException() {
        super(MESSAGE_PARSE_EXCEPTION_EMPTY_COMMAND);
    }
}
