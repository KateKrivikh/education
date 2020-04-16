package main.java.com.education.controller.command;

import main.java.com.education.exceptions.domain.OperationIsEmptyException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;

/**
 * Command of CRUD-operation.
 * Depends on operation. Has some parameters.
 */
public abstract class CrudCommand extends Command {
    protected Operation operation;
    protected int parametersCount;

    public CrudCommand(Operation operation, int parametersCount) throws OperationIsEmptyException {
        if (operation == null)
            throw new OperationIsEmptyException();

        this.operation = operation;
        this.parametersCount = parametersCount;
    }

    public abstract void setParameters(String... parameters) throws IncorrectInputException;


    public Operation getOperation() {
        return operation;
    }

    public int getParametersCount() {
        return parametersCount;
    }
}
