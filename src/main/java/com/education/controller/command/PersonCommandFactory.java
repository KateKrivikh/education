package main.java.com.education.controller.command;

import main.java.com.education.exceptions.domain.OperationIsEmptyException;

public class PersonCommandFactory {

    public static PersonCommand create(Operation operation) throws OperationIsEmptyException {
        if (operation == null)
            throw new OperationIsEmptyException();

        switch (operation) {
            case ADD:
                return new PersonCommandAdd();
            case UPDATE:
                return new PersonCommandUpdate();
            case REMOVE:
                return new PersonCommandRemove();
            case INFO:
                return new PersonCommandInfo();
        }
        throw new OperationIsEmptyException();
    }
}
