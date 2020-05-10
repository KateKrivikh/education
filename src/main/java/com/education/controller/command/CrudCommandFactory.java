package com.education.controller.command;

import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;

/**
 * Factory creates CrudCommand.
 * For now there are only person commands.
 */
public class CrudCommandFactory {

    public static CrudCommand create(String commandName, String[] commandParameters) throws IncorrectInputException {
        Operation operation = InputParser.parseOperation(commandName);
        return create(operation, commandParameters);
    }

    public static CrudCommand create(Operation operation, String[] commandParameters) throws IncorrectInputException {
        CrudCommand command = PersonCommandFactory.create(operation);
        command.setParameters(commandParameters);

        return command;
    }
}
