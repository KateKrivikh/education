package com.education.exceptions.incorrectInput;

import com.education.commands.Command;

public class IncorrectOperationParametersCount extends IncorrectInputException {
    public static final String MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT =
            "Неверное количество параметров для команды %s\nОжидаемое количество параметров: %d";

    private IncorrectOperationParametersCount(String message) {
        super(message);
    }

    // TODO Наверное, надо вынести количество параметров для команды в enum.
    // TODO Подумать, может Command вообще интерфейсом сделать (Executable).
    public IncorrectOperationParametersCount(Command command, int parametersCount) {
        this(String.format(MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT,
                command.getOperation(), parametersCount));
    }
}
