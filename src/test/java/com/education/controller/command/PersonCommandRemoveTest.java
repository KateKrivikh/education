package test.java.com.education.controller.command;

import main.java.com.education.controller.command.PersonCommand;
import main.java.com.education.controller.command.PersonCommandRemove;
import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.exceptions.domain.PersonNotFoundException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectIdException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectOperationParametersCountException;
import org.junit.Assert;
import org.junit.Test;
import test.java.com.education.UtilTest;

public class PersonCommandRemoveTest {

    public static final String MESSAGE_INCORRECT_INPUT_EXCEPTION = PersonCommandRemove.MESSAGE_REMOVE_EXCEPTION;

    @Test
    public void setParameters() {
        int id = 1;
        String idString = "1";

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }
        Assert.assertEquals(id, command.getId());
        Assert.assertNull(command.getName());
        Assert.assertNull(command.getSex());
        Assert.assertNull(command.getBirthDate());
    }

    @Test
    public void setParametersNotEnoughParameters() {
        String[] parameters = {};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности количества параметров");
        } catch (IncorrectInputException actual) {
            IncorrectOperationParametersCountException cause = new IncorrectOperationParametersCountException(command.getParametersCount());
            IncorrectInputException expected = new IncorrectInputException(MESSAGE_INCORRECT_INPUT_EXCEPTION, cause);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void setParametersTooManyParameters() {
        String idString = "1";
        String name = "Сидоров";

        String[] parameters = {idString, name};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности количества параметров");
        } catch (IncorrectInputException actual) {
            IncorrectOperationParametersCountException cause = new IncorrectOperationParametersCountException(command.getParametersCount());
            IncorrectInputException expected = new IncorrectInputException(MESSAGE_INCORRECT_INPUT_EXCEPTION, cause);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void setParametersWrongParameters() {
        String idStringWrong = "wrong";

        String[] parameters = {idStringWrong};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности указанного пола");
        } catch (IncorrectInputException actual) {
            IncorrectIdException cause = new IncorrectIdException(idStringWrong);
            IncorrectInputException expected = new IncorrectInputException(MESSAGE_INCORRECT_INPUT_EXCEPTION, cause);
            Assert.assertEquals(expected, actual);
        }
    }


    @Test
    public void executeExisting() {
        UtilTest.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = UtilTest.getMinPersonId();

        int id = minId;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertNull(command.getResult());

        Person actual = PersonRepository.getById(id);
        Assert.assertNull(actual.getName());
        Assert.assertNull(actual.getSex());
        Assert.assertNull(actual.getBirthDate());
    }

    @Test
    public void executeRemoved() {
        UtilTest.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = UtilTest.getMinPersonId();

        int id = minId + 1;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertNull(command.getResult());

        Person actual = PersonRepository.getById(id);
        Assert.assertNull(actual.getName());
        Assert.assertNull(actual.getSex());
        Assert.assertNull(actual.getBirthDate());
    }

    @Test
    public void executePersonNotFound() {
        UtilTest.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = UtilTest.getMinPersonId();

        int id = minId + 4;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandRemove();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }

        try {
            command.execute();
        } catch (PersonNotFoundException actual) {
            PersonNotFoundException expected = new PersonNotFoundException(id);
            Assert.assertEquals(expected, actual);
        }
        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertNull(command.getResult());
    }

}