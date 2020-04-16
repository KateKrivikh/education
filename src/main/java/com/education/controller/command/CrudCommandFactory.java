package main.java.com.education.controller.command;

import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;

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
