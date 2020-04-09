package com.education.commands;

import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.domain.OperationIsEmptyException;
import com.education.exceptions.incorrectInput.IncorrectInputException;

public abstract class Command {
    private Operation operation;
    private int parametersCount;

    public Command(Operation operation, int parametersCount) throws OperationIsEmptyException {
        if (operation == null)
            throw new OperationIsEmptyException();

        this.operation = operation;
        this.parametersCount = parametersCount;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getParametersCount() {
        return parametersCount;
    }

    public abstract int execute(String[] args) throws IncorrectInputException, DomainExceptions;

}
