package main.java.com.education.controller.command;

import main.java.com.education.exceptions.domain.OperationIsEmptyException;

public class CommandFactory {

    public static PersonCommand create(Operation operation) throws OperationIsEmptyException {
        if (operation == null)
            throw new OperationIsEmptyException();

        switch (operation) {
            case ADD:
                return new PersonCommandAdd(operation);
            case UPDATE:
                return new PersonCommandUpdate(operation);
            case REMOVE:
                return new PersonCommandRemove(operation);
            case INFO:
                return new PersonCommandInfo(operation);
        }
        throw new OperationIsEmptyException();
    }
}
