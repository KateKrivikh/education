package test.java.com.education.controller.command;

import main.java.com.education.controller.command.PersonCommand;
import main.java.com.education.controller.command.PersonCommandUpdate;
import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.domain.PersonNotFoundException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectDateException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectOperationParametersCountException;
import main.java.com.education.util.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PersonCommandUpdateTest {

    public static final String MESSAGE_INCORRECT_INPUT_EXCEPTION = PersonCommandUpdate.MESSAGE_UPDATE_EXCEPTION;

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
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }
        Assert.assertEquals(id, command.getId());
        Assert.assertEquals(name, command.getName());
        Assert.assertEquals(sex, command.getSex());
        Assert.assertEquals(birthDate, command.getBirthDate());
    }

    @Test
    public void setParametersNotEnoughParameters() {
        String idString = "1";
        String name = "Сидоров";

        String[] parameters = {idString, name};
        PersonCommand command = new PersonCommandUpdate();

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
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateString = "13/04/2003";
        String someParameter = "something strange...";

        String[] parameters = {idString, name, sexString, birthDateString, someParameter};
        PersonCommand command = new PersonCommandUpdate();

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
        String idString = "1";
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateStringWrong = "13-04-2003";

        String[] parameters = {idString, name, sexString, birthDateStringWrong};
        PersonCommand command = new PersonCommandUpdate();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности указанного пола");
        } catch (IncorrectInputException actual) {
            IncorrectDateException cause = new IncorrectDateException(birthDateStringWrong, InputParser.DATE_FORMAT_FOR_INPUT);
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
            Assert.fail("Возникло непредвиденное исключение");
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
        fillData();

        int expectedSize = PersonRepository.getAll().size();
        int first = getFirstInThisIteration();

        int id = first + 1;
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
            Assert.fail("Возникло непредвиденное исключение");
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
    public void executePersonNotFound() {
        fillData();

        int expectedSize = PersonRepository.getAll().size();
        int first = getFirstInThisIteration();

        int id = first + 4;
        String idString = String.valueOf(id);
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        String birthDateString = "13/04/2003";

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandUpdate();

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


    private int getFirstInThisIteration() {
        return PersonRepository.getAll().stream().map(Person::getId).min(Integer::compareTo).orElse(0);
    }
}