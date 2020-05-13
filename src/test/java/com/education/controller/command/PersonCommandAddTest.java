package com.education.controller.command;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.entities.Sex;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;
import com.education.util.OutputBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCommandAddTest {

    PersonCommand command;
    String[] parameters;

    String name;
    Sex sex;
    String sexString;
    LocalDate birthDate;
    String birthDateString;

    @Before
    public void fillParameters() {
        command = new PersonCommandAdd();

        name = "Сидоров";
        sex = Sex.MALE;
        sexString = InputParser.PARAM_SEX_MALE;
        birthDate = LocalDate.of(2003, 4, 13);
        birthDateString = "13/04/2003";
    }

    @Test
    public void setParameters_NameSexDate_True() {
        Given:
        parameters = new String[]{name, sexString, birthDateString};

        When:
        command.setParameters(parameters);

        Then:
        {
            Assert.assertEquals(name, command.getName());
            Assert.assertEquals(sex, command.getSex());
            Assert.assertEquals(birthDate, command.getBirthDate());
        }
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_NameSex_ExceptionNotEnoughParameters() {
        Given:
        parameters = new String[]{name, sexString};

        When:
        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_IdNameSexDate_ExceptionTooManyParameters() {
        Given:
        parameters = new String[]{"1", name, sexString, birthDateString};

        When:
        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_WrongSex_Exception() {
        Given:
        {
            String sexStringWrong = "male";
            parameters = new String[]{name, sexStringWrong, birthDateString};
        }

        When:
        command.setParameters(parameters);
    }


    @Test
    public void execute_RightParameters_AddedToRepository() {
        int givenRepositorySize;

        Given:
        {
            givenRepositorySize = PersonRepository.getAll().size();

            parameters = new String[]{name, sexString, birthDateString};
            command.setParameters(parameters);
        }

        When:
        command.execute();

        Then:
        {
            Assert.assertEquals(givenRepositorySize + 1, PersonRepository.getAll().size());
            Assert.assertEquals(OutputBuilder.getPersonAddedMessage(command.getId()), command.getResult());

            Person actual = PersonRepository.getById(command.getId());
            Assert.assertEquals(name, actual.getName());
            Assert.assertEquals(sex, actual.getSex());
            Assert.assertEquals(birthDate, actual.getBirthDate());
        }
    }
}