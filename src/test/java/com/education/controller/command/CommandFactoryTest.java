package test.java.com.education.controller.command;

import main.java.com.education.controller.command.*;
import main.java.com.education.exceptions.domain.OperationIsEmptyException;
import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {

    @Test
    public void createAdd() {
        PersonCommand actual = CommandFactory.create(Operation.ADD);
        PersonCommand expected = new PersonCommandAdd();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createUpdate() {
        PersonCommand actual = CommandFactory.create(Operation.UPDATE);
        PersonCommand expected = new PersonCommandUpdate();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createRemove() {
        PersonCommand actual = CommandFactory.create(Operation.REMOVE);
        PersonCommand expected = new PersonCommandRemove();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createInfo() {
        PersonCommand actual = CommandFactory.create(Operation.INFO);
        PersonCommand expected = new PersonCommandInfo();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createNull() {
        Operation nullOperation = null;
        OperationIsEmptyException expected = new OperationIsEmptyException();
        try {
            CommandFactory.create(nullOperation);
        } catch (OperationIsEmptyException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        OperationIsEmptyException actual = null;
        Assert.assertEquals(expected, actual);
    }
}