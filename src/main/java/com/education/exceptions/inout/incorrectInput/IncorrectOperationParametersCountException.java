package main.java.com.education.exceptions.inout.incorrectInput;

import main.java.com.education.controller.commands.CommandPerson;

public class IncorrectOperationParametersCountException extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT =
            "Неверное количество параметров для команды %s\nОжидаемое количество параметров: %d";

    private IncorrectOperationParametersCountException(String message) {
        super(message);
    }

    public IncorrectOperationParametersCountException(CommandPerson command, int parametersCount) {
        this(String.format(MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT,
                command.getOperation(), parametersCount));
    }
}
