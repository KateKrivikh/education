package com.education.controller.command;

import com.education.PersonTestUtils;
import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.entities.Sex;
import com.education.exceptions.domain.PersonNotFoundException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCommandUpdateTest {

    PersonCommand command;
    String[] parameters;

    int id;
    String idString;
    String name;
    Sex sex;
    String sexString;
    LocalDate birthDate;
    String birthDateString;

    @Before
    public void fillParameters() {
        PersonTestUtils.fillPerson();
        int minId = PersonTestUtils.getMinPersonId();

        command = new PersonCommandUpdate();

        id = minId;
        idString = String.valueOf(id);
        name = "Сидоров";
        sex = Sex.MALE;
        sexString = InputParser.PARAM_SEX_MALE;
        birthDate = LocalDate.of(2003, 4, 13);
        birthDateString = "13/04/2003";
    }

    @Test
    public void setParameters_IdNameSexDate_True() {
        Given:
        parameters = new String[]{idString, name, sexString, birthDateString};

        When:
        command.setParameters(parameters);

        Then:
        {
            Assert.assertEquals(id, command.getId());
            Assert.assertEquals(name, command.getName());
            Assert.assertEquals(sex, command.getSex());
            Assert.assertEquals(birthDate, command.getBirthDate());
        }
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_IdName_ExceptionNotEnoughParameters() {
        Given:
        parameters = new String[]{idString, name};

        When:
        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_IdNameSexDateAnother_ExceptionTooManyParameters() {
        Given:
        {
            String someParameter = "something strange...";
            parameters = new String[]{idString, name, sexString, birthDateString, someParameter};
        }

        When:
        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_IdNameSexDateWrongFormat_Exception() {
        Given:
        {
            String birthDateStringWrong = "13-04-2003";
            parameters = new String[]{idString, name, sexString, birthDateStringWrong};
        }

        When:
        command.setParameters(parameters);
    }


    @Test
    public void execute_ExistingPerson_UpdatedInRepository() {
        int givenRepositorySize;

        Given:
        {
            givenRepositorySize = PersonRepository.getAll().size();
            parameters = new String[]{idString, name, sexString, birthDateString};
            command.setParameters(parameters);
        }

        When:
        command.execute();

        Then:
        {
            Assert.assertEquals(givenRepositorySize, PersonRepository.getAll().size());
            Assert.assertNull(command.getResult());

            Person actual = PersonRepository.getById(id);
            Assert.assertEquals(name, actual.getName());
            Assert.assertEquals(sex, actual.getSex());
            Assert.assertEquals(birthDate, actual.getBirthDate());
        }
    }

    @Test
    public void execute_RemovedPerson_UpdatedInRepository() {
        int givenRepositorySize;

        Given:
        {
            givenRepositorySize = PersonRepository.getAll().size();
            id++;
            idString = String.valueOf(id);
            parameters = new String[]{idString, name, sexString, birthDateString};
            command.setParameters(parameters);
        }

        When:
        command.execute();

        Then:
        {
            Assert.assertEquals(givenRepositorySize, PersonRepository.getAll().size());
            Assert.assertNull(command.getResult());

            Person actual = PersonRepository.getById(id);
            Assert.assertEquals(name, actual.getName());
            Assert.assertEquals(sex, actual.getSex());
            Assert.assertEquals(birthDate, actual.getBirthDate());
        }
    }

    @Test(expected = PersonNotFoundException.class)
    public void execute_NoSuchIdInRepository_Exception() {
        Given:
        {
            id += 4;
            idString = String.valueOf(id);
            parameters = new String[]{idString, name, sexString, birthDateString};
            command.setParameters(parameters);
        }

        When:
        command.execute();
    }

}