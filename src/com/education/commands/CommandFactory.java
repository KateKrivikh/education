package com.education.commands;

import com.education.exceptions.domain.OperationIsEmptyException;

public class CommandFactory {

    public static CommandPerson create(Operation operation) throws OperationIsEmptyException {
        if (operation == null)
            throw new OperationIsEmptyException();

        switch (operation) {
            case ADD:
                return new CommandAdd(operation);
            case UPDATE:
                return new CommandUpdate(operation);
            case REMOVE:
                return new CommandRemove(operation);
            case INFO:
                return new CommandInfo(operation);
        }
        throw new OperationIsEmptyException();
    }
}
