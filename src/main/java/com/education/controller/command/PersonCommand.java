package main.java.com.education.controller.command;

import main.java.com.education.exceptions.domain.OperationIsEmptyException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.entities.Sex;

import java.time.LocalDate;
import java.util.Objects;

public abstract class PersonCommand extends Command {
    private Operation operation;
    private int parametersCount;

    protected int id;
    protected String name;
    protected Sex sex;
    protected LocalDate birthDate;

    public PersonCommand(Operation operation, int parametersCount) throws OperationIsEmptyException {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonCommand)) return false;
        PersonCommand that = (PersonCommand) o;
        if (!Objects.equals(name, that.name)) return false;

        return Objects.equals(result, that.result) &&
                parametersCount == that.parametersCount &&
                operation == that.operation &&
                id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(birthDate, that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, parametersCount, operation, id, name, sex, birthDate);
    }
}
