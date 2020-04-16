package main.java.com.education.controller;

import main.java.com.education.controller.command.Command;
import main.java.com.education.controller.command.CrudCommandFactory;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.exceptions.inout.incorrectInput.EmptyCommandException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;

import java.util.Arrays;

public abstract class Controller {
    public abstract String read();

    public abstract void write(String info);


    public Command parse(String commandString) throws IncorrectInputException {
        if (commandString == null || commandString.trim().isEmpty())
            throw new EmptyCommandException();

        String[] words = commandString.split("\\s");// TODO name consists of several words

        String commandName = words[0];
        String[] commandParameters = Arrays.copyOfRange(words, 1, words.length);

        return createCommand(commandName, commandParameters);
    }

    protected Command createCommand(String commandName, String[] commandParameters) throws IncorrectInputException {
        return CrudCommandFactory.create(commandName, commandParameters);
    }

    public void execute(Command command) throws DomainExceptions {
        actionsBeforeExecuting(command);
        command.execute();
        actionsAfterExecuting(command);
    }

    public void actionsBeforeExecuting(Command command) throws DomainExceptions {

    }

    public void actionsAfterExecuting(Command command) throws DomainExceptions {
        if (command.getResult() != null)
            write(command.getResult());
    }
}
