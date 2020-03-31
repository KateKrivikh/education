package com.education.inout;

import com.education.commands.Command;
import com.education.commands.CommandFactory;
import com.education.commands.Operation;
import com.education.exceptions.ExpectingCommandException;
import com.education.exceptions.IncorrectInputException;
import com.education.exceptions.PersonNotFoundException;

import java.util.Arrays;

public abstract class Controller {
    public abstract String[] getCommand() throws ExpectingCommandException;

    public void executeCommand(String[] args) throws IncorrectInputException, PersonNotFoundException {
        if (!actionsBeforeCommand(args))
            return;

        Operation operation = InputParser.parseOperation(args[0]);
        Command command = CommandFactory.create(operation);
        int id = command.execute(Arrays.copyOfRange(args, 1, args.length));

        actionsAfterCommand(operation, id);
    }

    public boolean actionsBeforeCommand(String[] args) {
        return true;
    }

    public abstract void actionsAfterCommand(Operation operation, int id);

    public abstract void write(String info);
}
