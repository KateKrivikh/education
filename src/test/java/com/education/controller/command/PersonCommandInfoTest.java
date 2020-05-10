package com.education.controller.command;

import com.education.PersonTestUtils;
import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.exceptions.domain.PersonNotFoundException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.OutputBuilder;
import org.junit.Assert;
import org.junit.Test;

public class PersonCommandInfoTest {

    @Test
    public void setParameters() {
        int id = 1;
        String idString = "1";

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }
        Assert.assertEquals(id, command.getId());
        Assert.assertNull(command.getName());
        Assert.assertNull(command.getSex());
        Assert.assertNull(command.getBirthDate());
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersNotEnoughParameters() {
        String[] parameters = {};
        PersonCommand command = new PersonCommandInfo();

        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersTooManyParameters() {
        String idString = "1";
        String name = "Сидоров";

        String[] parameters = {idString, name};
        PersonCommand command = new PersonCommandInfo();

        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersWrongParameters() {
        String idStringWrong = "wrong";

        String[] parameters = {idStringWrong};
        PersonCommand command = new PersonCommandInfo();

        command.setParameters(parameters);
    }


    @Test
    public void executeExisting() {
        PersonTestUtils.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = PersonTestUtils.getMinPersonId();

        int id = minId;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());

        Person actual = PersonRepository.getById(id);
        String expected = String.join(OutputBuilder.MESSAGE_INFO_DELIMITER,
                actual.getName(), OutputBuilder.writeSex(actual.getSex()), OutputBuilder.writeDate(actual.getBirthDate()));
        Assert.assertEquals(expected, command.getResult());
    }

    @Test
    public void executeRemoved() {
        PersonTestUtils.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = PersonTestUtils.getMinPersonId();

        int id = minId + 1;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());

        String expected = String.format(OutputBuilder.MESSAGE_INFO_FOR_REMOVED_PERSON, id);
        Assert.assertEquals(expected, command.getResult());
    }

    @Test(expected = PersonNotFoundException.class)
    public void executePersonNotFound() {
        PersonTestUtils.fillPerson();

        int minId = PersonTestUtils.getMinPersonId();

        int id = minId + 4;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }

        command.execute();
    }

}