package main.java.com.education.exceptions.inout.incorrectInput;

public class IncorrectDateException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_DATE =
            "Не удалось определить дату по параметру %s\nОжидаемый формат: %s";

    private IncorrectDateException(String message) {
        super(message);
    }

    public IncorrectDateException(String dateString, String expectedFormat) {
        this(String.format(MESSAGE_PARSE_EXCEPTION_DATE, dateString, expectedFormat));
    }
}
