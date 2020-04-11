package main.java.com.education.exceptions.inout.incorrectInput;

public class EmptyParameterException extends IncorrectInputException {

    public static final String MESSAGE_PARSE_EXCEPTION_EMPTY_PARAMETER = "Пустой параметр: ";

    public EmptyParameterException(String message) {
        super(MESSAGE_PARSE_EXCEPTION_EMPTY_PARAMETER + message);
    }
}
