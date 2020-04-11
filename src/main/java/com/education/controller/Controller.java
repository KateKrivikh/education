package main.java.com.education.controller;

import main.java.com.education.controller.commands.Command;
import main.java.com.education.controller.commands.CommandFactory;
import main.java.com.education.controller.commands.Operation;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.controller.commands.CommandPerson;
import main.java.com.education.exceptions.inout.InOutException;
import main.java.com.education.exceptions.inout.incorrectInput.EmptyCommandException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;

import java.util.Arrays;

public abstract class Controller {
    public abstract String getCommandString();

    public Command parseCommand(String commandString) throws IncorrectInputException {
        if (commandString == null || commandString.trim().isEmpty())
            throw new EmptyCommandException();

        String[] words = commandString.split("\\s");// TODO name состоит из нескольких слов

        Operation operation = InputParser.parseOperation(words[0]);
        CommandPerson command = CommandFactory.create(operation);

        String[] commandParameters = Arrays.copyOfRange(words, 1, words.length);
        command.setParameters(commandParameters);

        return command;
    }

    public void executeCommand(Command command) throws InOutException, DomainExceptions {
        if (!actionsBeforeCommand(command))
            return;

        command.execute();

        actionsAfterCommand(command);
    }

    public boolean actionsBeforeCommand(Command command) {
        return true;
    }

    public abstract void actionsAfterCommand(Command command);

    public abstract void write(String info);
}
