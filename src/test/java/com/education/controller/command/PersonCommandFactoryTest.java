package com.education.controller.command;

import com.education.exceptions.domain.OperationIsEmptyException;
import org.junit.Assert;
import org.junit.Test;

public class PersonCommandFactoryTest {

    @Test
    public void createAdd() {
        PersonCommand actual = PersonCommandFactory.create(Operation.ADD);
        PersonCommand expected = new PersonCommandAdd();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createUpdate() {
        PersonCommand actual = PersonCommandFactory.create(Operation.UPDATE);
        PersonCommand expected = new PersonCommandUpdate();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createRemove() {
        PersonCommand actual = PersonCommandFactory.create(Operation.REMOVE);
        PersonCommand expected = new PersonCommandRemove();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createInfo() {
        PersonCommand actual = PersonCommandFactory.create(Operation.INFO);
        PersonCommand expected = new PersonCommandInfo();
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = OperationIsEmptyException.class)
    public void createNull() {
        Operation nullOperation = null;
        PersonCommandFactory.create(nullOperation);
    }
}