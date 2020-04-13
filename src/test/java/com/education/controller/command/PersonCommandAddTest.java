package test.java.com.education.controller.command;

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
import java.time.format.DateTimeFormatter;

public class PersonCommandAddTest {

    @Test
    public void setParametersMale() {
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InputParser.DATE_FORMAT_FOR_INPUT);
        String birthDateString = formatter.format(birthDate);

        String[] parameters = {name, sexString, birthDateString};
        PersonCommandAdd command = new PersonCommandAdd();

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
        PersonCommandAdd command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности количества параметров");
        } catch (IncorrectInputException actual) {
            Assert.assertEquals(new IncorrectOperationParametersCountException(command.getParametersCount()), actual);
        }
    }

    @Test
    public void setParametersTooManyParameters() {
        String id = "1";
        String name = "Сидоров";
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InputParser.DATE_FORMAT_FOR_INPUT);
        String birthDateString = formatter.format(birthDate);


        String[] parameters = {id, name, sexString, birthDateString};
        PersonCommandAdd command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности количества параметров");
        } catch (IncorrectInputException actual) {
            Assert.assertEquals(new IncorrectOperationParametersCountException(command.getParametersCount()), actual);
        }
    }

    @Test
    public void setParametersWrongParameters() {
        String name = "Сидоров";
        LocalDate birthDate = LocalDate.of(2003, 4, 13);

        String sexStringWrong = "male";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InputParser.DATE_FORMAT_FOR_INPUT);
        String birthDateString = formatter.format(birthDate);


        String[] parameters = {name, sexStringWrong, birthDateString};
        PersonCommandAdd command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
            Assert.fail("Должно быть исключение о неверности указанного пола");
        } catch (IncorrectInputException actual) {
            Assert.assertEquals(new IncorrectSexException(sexStringWrong, InputParser.SEX_FORMATS_FOR_MESSAGE), actual);
        }
    }


    @Test
    public void execute() {
        String name = "Сидоров";
        Sex sex = Sex.MALE;
        String sexString = InputParser.PARAM_SEX_MALE;
        LocalDate birthDate = LocalDate.of(2003, 4, 13);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InputParser.DATE_FORMAT_FOR_INPUT);
        String birthDateString = formatter.format(birthDate);

        String[] parameters = {name, sexString, birthDateString};
        PersonCommandAdd command = new PersonCommandAdd();

        try {
            command.setParameters(parameters);
        } catch (IncorrectInputException e) {
            Assert.fail("Возникло непредвиденное исключение");
            e.printStackTrace();
            return;
        }

        command.execute();

        Person actual = PersonRepository.getById(command.getId());
        Assert.assertEquals(name, actual.getName());
        Assert.assertEquals(sex, actual.getSex());
        Assert.assertEquals(birthDate, actual.getBirthDate());

        Assert.assertEquals(String.format(PersonCommandAdd.MESSAGE_ADD, actual.getId()), command.getResult());
    }
}