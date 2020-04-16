package main.java.com.education.controller.command;

import main.java.com.education.entities.Sex;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CRUD-operation for class Person.
 * Has parameters: id, name, sex, birthDate.
 */
public abstract class PersonCommand extends CrudCommand {
    protected int id;
    protected String name;
    protected Sex sex;
    protected LocalDate birthDate;

    public PersonCommand(Operation operation, int parametersCount) {
        super(operation, parametersCount);
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


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
