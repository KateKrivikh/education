package main.java.com.education.controller.commands;

import main.java.com.education.exceptions.domain.OperationIsEmptyException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.entities.Sex;

import java.time.LocalDate;

public abstract class CommandPerson extends Command {
    private Operation operation;
    private int parametersCount;

    protected int id;
    protected String name;
    protected Sex sex;
    protected LocalDate birthDate;

    public CommandPerson(Operation operation, int parametersCount) throws OperationIsEmptyException {
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

    public int getId() {
        return id;
    }

}
