package com.education.controller.command;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.entities.Sex;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCommandAddTest {

    @Test
    public void setParameters() {
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);
        String birthDateString = "13/04/2003";

        String[] parameters = {name, sexString, birthDateString};
        PersonCommand command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }
        Assert.assertEquals(name, command.getName());
        Assert.assertEquals(sex, command.getSex());
        Assert.assertEquals(birthDate, command.getBirthDate());
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersNotEnoughParameters() {
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;

        String[] parameters = {name, sexString};
        PersonCommand command = new PersonCommandAdd();

        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersTooManyParameters() {
        String idString = "1";
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateString = "13/04/2003";

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandAdd();

        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParametersWrongParameters() {
        String name = "Сидоров";
        String sexStringWrong = "male";
        String birthDateString = "13/04/2003";

        String[] parameters = {name, sexStringWrong, birthDateString};
        PersonCommand command = new PersonCommandAdd();

        command.setParameters(parameters);
    }


    @Test
    public void execute() {
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);
        String birthDateString = "13/04/2003";
        int expectedSize = PersonRepository.getAll().size() + 1;

        String[] parameters = {name, sexString, birthDateString};
        PersonCommand command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Unexpected exception");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertEquals(String.format(PersonCommandAdd.MESSAGE_ADD, command.getId()), command.getResult());

        Person actual = PersonRepository.getById(command.getId());
        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(sex, actual.getSex());
        Assert.assertEquals(birthDate, actual.getBirthDate());
    }
}