package com.education.controller.command;

import com.education.PersonTestUtils;
import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.entities.Sex;
import com.education.exceptions.domain.PersonNotFoundException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCommandUpdateTest {

    @Test
    public void setParameters() {
        int id = 1;
        String idString = "1";
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);
        String birthDateString = "13/04/2003";

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandUpdate();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }
        Assert.assertEquals(id, command.getId());
        Assert.assertEquals(name, command.getName());
        Assert.assertEquals(sex, command.getSex());
        Assert.assertEquals(birthDate, command.getBirthDate());
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersNotEnoughParameters() {
        String idString = "1";
        String name = "Сидоров";

        String[] parameters = {idString, name};
        PersonCommand command = new PersonCommandUpdate();

        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersTooManyParameters() {
        String idString = "1";
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateString = "13/04/2003";
        String someParameter = "something strange...";

        String[] parameters = {idString, name, sexString, birthDateString, someParameter};
        PersonCommand command = new PersonCommandUpdate();

        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersWrongParameters() {
        String idString = "1";
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateStringWrong = "13-04-2003";

        String[] parameters = {idString, name, sexString, birthDateStringWrong};
        PersonCommand command = new PersonCommandUpdate();

        command.setParameters(parameters);
    }


    @Test
    public void executeExisting() {
        PersonTestUtils.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = PersonTestUtils.getMinPersonId();

        int id = minId;
        String idString = String.valueOf(id);
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);
        String birthDateString = "13/04/2003";

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandUpdate();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertNull(command.getResult());

        Person actual = PersonRepository.getById(id);
        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(sex, actual.getSex());
        Assert.assertEquals(birthDate, actual.getBirthDate());
    }

    @Test
    public void executeRemoved() {
        PersonTestUtils.fillPerson();

        int expectedSize = PersonRepository.getAll().size();
        int minId = PersonTestUtils.getMinPersonId();

        int id = minId + 1;
        String idString = String.valueOf(id);
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);
        String birthDateString = "13/04/2003";

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandUpdate();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertNull(command.getResult());

        Person actual = PersonRepository.getById(id);
        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(sex, actual.getSex());
        Assert.assertEquals(birthDate, actual.getBirthDate());
    }

    @Test(expected = PersonNotFoundException.class)
    public void executePersonNotFound() {
        PersonTestUtils.fillPerson();

        int minId = PersonTestUtils.getMinPersonId();

        int id = minId + 4;
        String idString = String.valueOf(id);
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateString = "13/04/2003";

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandUpdate();

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