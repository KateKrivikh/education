package test.java.com.education.controller.command;

import main.java.com.education.controller.command.PersonCommand;
import main.java.com.education.controller.command.PersonCommandAdd;
import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectOperationParametersCountException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectSexException;
import main.java.com.education.util.InputParser;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class PersonCommandAddTest {

    public static final String MESSAGE_INCORRECT_INPUT_EXCEPTION = PersonCommandAdd.MESSAGE_ADD_EXCEPTION;

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
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }
        Assert.assertEquals(name, command.getName());
        Assert.assertEquals(sex, command.getSex());
        Assert.assertEquals(birthDate, command.getBirthDate());
    }

    @Test
    public void setParametersNotEnoughParameters() {
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;

        String[] parameters = {name, sexString};
        PersonCommand command = new PersonCommandAdd();

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

        String[] parameters = {idString, name, sexString, birthDateString};
        PersonCommand command = new PersonCommandAdd();

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
        String name = "Сидоров";
        String sexStringWrong = "male";
        String birthDateString = "13/04/2003";

        String[] parameters = {name, sexStringWrong, birthDateString};
        PersonCommand command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности указанного пола");
        } catch (IncorrectInputException actual) {
            IncorrectSexException cause = new IncorrectSexException(sexStringWrong, InputParser.SEX_FORMATS_FOR_MESSAGE);
            IncorrectInputException expected = new IncorrectInputException(MESSAGE_INCORRECT_INPUT_EXCEPTION, cause);
            Assert.assertEquals(expected, actual);
        }
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
            Assert.fail("Возникло непредвиденное исключение");
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