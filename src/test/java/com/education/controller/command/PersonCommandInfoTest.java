package test.java.com.education.controller.command;

import main.java.com.education.controller.command.PersonCommand;
import main.java.com.education.controller.command.PersonCommandInfo;
import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.domain.PersonNotFoundException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectIdException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectOperationParametersCountException;
import main.java.com.education.util.OutputBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PersonCommandInfoTest {

    public static final String MESSAGE_INCORRECT_INPUT_EXCEPTION = PersonCommandInfo.MESSAGE_INFO_EXCEPTION;

    private List<Person> getPeople() {
        Person person1 = new Person("Иванов Иван", Sex.MALE, LocalDate.of(2000, 1, 1));
        Person person2 = new Person(null, null, null);

        return Arrays.asList(person1, person2);
    }

    private void fillData() {
        List<Person> all = getPeople();

        PersonRepository.clear();
        for (Person person : all) {
            PersonRepository.save(person);
        }
    }

    @Test
    public void setParameters() {
        int id = 1;
        String idString = "1";

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

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
        PersonCommand command = new PersonCommandInfo();

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
        PersonCommand command = new PersonCommandInfo();

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
        PersonCommand command = new PersonCommandInfo();

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
        fillData();

        int expectedSize = PersonRepository.getAll().size();
        int first = getFirstInThisIteration();

        int id = first;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
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
        fillData();

        int expectedSize = PersonRepository.getAll().size();
        int first = getFirstInThisIteration();

        int id = first + 1;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }

        command.execute();

        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());

        String expected = String.format(OutputBuilder.MESSAGE_INFO_FOR_REMOVED_PERSON, id);
        Assert.assertEquals(expected, command.getResult());
    }

    @Test
    public void executePersonNotFound() {
        fillData();

        int expectedSize = PersonRepository.getAll().size();
        int first = getFirstInThisIteration();

        int id = first + 4;
        String idString = String.valueOf(id);

        String[] parameters = {idString};
        PersonCommand command = new PersonCommandInfo();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }

        try {
            command.execute();
            Assert.fail("Должно быть исключение о том, что не нашли человека");
        } catch (PersonNotFoundException actual) {
            PersonNotFoundException expected = new PersonNotFoundException(id);
            Assert.assertEquals(expected, actual);
        }
        Assert.assertEquals(expectedSize, PersonRepository.getAll().size());
        Assert.assertNull(command.getResult());
    }


    private int getFirstInThisIteration() {
        return PersonRepository.getAll().stream().map(Person::getId).min(Integer::compareTo).orElse(0);
    }
}