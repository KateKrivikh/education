package com.education.controller.command;

import com.education.exceptions.domain.OperationIsEmptyException;
import org.junit.Assert;
import org.junit.Test;

public class PersonCommandFactoryTest {

    Operation operation;
    PersonCommand command;

    @Test
    public void create_AddOperation_AddCommand() {
        Given:
        operation = Operation.ADD;

        When:
        command = PersonCommandFactory.create(operation);

        Then:
        Assert.assertTrue(command instanceof PersonCommandAdd);
    }

    @Test
    public void create_UpdateOperation_UpdateCommand() {
        Given:
        operation = Operation.UPDATE;

        When:
        command = PersonCommandFactory.create(operation);

        Then:
        Assert.assertTrue(command instanceof PersonCommandUpdate);
    }

    @Test
    public void create_RemoveOperation_RemoveCommand() {
        Given:
        operation = Operation.REMOVE;

        When:
        command = PersonCommandFactory.create(operation);

        Then:
        Assert.assertTrue(command instanceof PersonCommandRemove);
    }

    @Test
    public void create_InfoOperation_InfoCommand() {
        Given:
        operation = Operation.INFO;

        When:
        command = PersonCommandFactory.create(operation);

        Then:
        Assert.assertTrue(command instanceof PersonCommandInfo);
    }

    @Test(expected = OperationIsEmptyException.class)
    public void create_Null_Exception() {
        Given:
        operation = null;

        When:
        command = PersonCommandFactory.create(operation);
    }
}