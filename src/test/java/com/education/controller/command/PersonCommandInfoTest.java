package com.education.controller.command;

import com.education.PersonTestUtils;
import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.exceptions.domain.PersonNotFoundException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.OutputBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PersonCommandInfoTest {

    PersonCommand command;
    String[] parameters;

    int id;
    String idString;

    @Before
    public void fillParameters() {
        PersonTestUtils.fillPerson();
        int minId = PersonTestUtils.getMinPersonId();

        command = new PersonCommandInfo();

        id = minId;
        idString = String.valueOf(id);
    }

    @Test
    public void setParameters_Id_True() {
        Given:
        parameters = new String[]{idString};

        When:
        command.setParameters(parameters);

        Then:
        {
            Assert.assertEquals(id, command.getId());
            Assert.assertNull(command.getName());
            Assert.assertNull(command.getSex());
            Assert.assertNull(command.getBirthDate());
        }
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_EmptyArray_ExceptionNotEnoughParameters() {
        Given:
        parameters = new String[]{};

        When:
        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_IdName_ExceptionTooManyParameters() {
        Given:
        {
            String name = "Сидоров";
            parameters = new String[]{idString, name};

        }

        When:
        command.setParameters(parameters);
    }

    @Test(expected = IncorrectInputException.class)
    public void setParameters_IdWrongFormat_Exception() {
        Given:
        {
            String idStringWrong = "notId";
            parameters = new String[]{idStringWrong};
        }

        When:
        command.setParameters(parameters);
    }


    @Test
    public void execute_ExistingPerson_ResultIsFilled() {
        int givenRepositorySize;

        Given:
        {
            givenRepositorySize = PersonRepository.getAll().size();
            parameters = new String[]{idString};
            command.setParameters(parameters);
        }

        When:
        command.execute();

        Then:
        {
            Assert.assertEquals(givenRepositorySize, PersonRepository.getAll().size());

            Person actual = PersonRepository.getById(id);
            String expected = OutputBuilder.getPersonInfo(id, actual.getName(), actual.getSex(), actual.getBirthDate());
            Assert.assertEquals(expected, command.getResult());
        }
    }

    @Test
    public void execute_RemovedPerson_ResultIsFilled() {
        int givenRepositorySize;

        Given:
        {
            givenRepositorySize = PersonRepository.getAll().size();
            id++;
            idString = String.valueOf(id);
            parameters = new String[]{idString};
            command.setParameters(parameters);
        }

        When:
        command.execute();

        Then:
        {
            Assert.assertEquals(givenRepositorySize, PersonRepository.getAll().size());

            String expected = OutputBuilder.getPersonInfo(id, null, null, null);
            Assert.assertEquals(expected, command.getResult());
        }
    }

    @Test(expected = PersonNotFoundException.class)
    public void execute_NoSuchIdInRepository_Exception() {
        Given:
        {
            id += 4;
            idString = String.valueOf(id);
            parameters = new String[]{idString};
            command.setParameters(parameters);
        }

        When:
        command.execute();
    }

}