package com.education.inout;

import com.education.commands.Command;
import com.education.commands.CommandFactory;
import com.education.commands.CommandPerson;
import com.education.commands.Operation;
import com.education.exceptions.InOutException;
import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.incorrectInput.EmptyOperationException;
import com.education.exceptions.incorrectInput.IncorrectInputException;

import java.util.Arrays;

public abstract class Controller {
    public abstract String getCommandString();

    public Command parseCommand(String commandString) throws IncorrectInputException {
        if (commandString == null || commandString.isEmpty())
            throw new EmptyOperationException();

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
