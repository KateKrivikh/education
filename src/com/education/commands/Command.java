package com.education.commands;

import com.education.exceptions.IncorrectInputException;
import com.education.exceptions.PersonNotFoundException;

public abstract class Command {
    private Operation operation;
    private int parametersCount;

    public Command(Operation operation, int parametersCount) {
        this.operation = operation;
        this.parametersCount = parametersCount;
    }

    public Operation getOperation() {
        return operation;
    }

    public int getParametersCount() {
        return parametersCount;
    }

    public abstract int execute(String[] args) throws IncorrectInputException, PersonNotFoundException;

}
