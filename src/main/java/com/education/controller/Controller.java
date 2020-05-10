package com.education.controller;

import com.education.controller.command.Command;
import com.education.controller.command.CrudCommandFactory;
import com.education.exceptions.domain.DomainException;
import com.education.exceptions.inout.incorrectInput.EmptyCommandException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;

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

    public void execute(Command command) throws DomainException {
        actionsBeforeExecuting(command);
        command.execute();
        actionsAfterExecuting(command);
    }

    public void actionsBeforeExecuting(Command command) throws DomainException {

    }

    public void actionsAfterExecuting(Command command) throws DomainException {
        if (command.getResult() != null)
            write(command.getResult());
    }
}
