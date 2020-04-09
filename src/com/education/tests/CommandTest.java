package com.education.tests;

import com.education.PersonRepository;
import com.education.entities.Person;
import com.education.entities.Sex;
import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.incorrectInput.IncorrectInputException;
import com.education.exceptions.domain.PersonNotFoundException;
import com.education.inout.Controller;
import com.education.inout.ControllerConsole;
import com.education.inout.InputParser;
import com.education.inout.OutputBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

// TODO тесты пока самые общие
public class CommandTest {
    Controller controller = new ControllerConsole();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(InputParser.DATE_FORMAT_FOR_INPUT);

    public List<Person> getPeople() {
        try {
            Person person1 = Person.createMale("Иванов Иван", LocalDate.parse("01/01/2000", formatter));
            Person person2 = Person.createMale("Петров Петр", LocalDate.parse("03/03/2003", formatter));
            Person person3 = Person.createMale("Сергеев Сергей", LocalDate.parse("05/05/2005", formatter));

            return Arrays.asList(person1, person2, person3);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void fillData() {
        List<Person> all = getPeople();

        PersonRepository.clear();
        for (Person person : all) {
            PersonRepository.save(person);
        }
    }

    @Test
    public void commandAddTest() {
        fillData();
        List<Person> initial = PersonRepository.getAll();

        String name = "Семенов Семен";
        String sex = "м";
        String date = "24/03/1999";

        String[] args = {"-c", name, sex, date};
        try {
            controller.executeCommand(args);
        } catch (IncorrectInputException | DomainExceptions e) {
            controller.write(e.getMessage());
        }

        List<Person> actual = PersonRepository.getAll();

        Assert.assertEquals(initial.size() + 1, actual.size());

        for (Person person : actual) {
            if (!initial.contains(person)) {
                Assert.assertEquals(name, person.getName());
                Assert.assertEquals(Sex.MALE, person.getSex());
                Assert.assertEquals(date, formatter.format(person.getBirthDate()));
            }
        }
    }

    @Test
    public void commandUpdateTest() {
        fillData();
        List<Person> initial = PersonRepository.getAll();

        String id = String.valueOf(initial.get(1).getId());
        String name = "Семенов Семен";
        String sex = "м";
        String date = "24/03/1999";

        String[] args = {"-u", id, name, sex, date};
        try {
            controller.executeCommand(args);
        } catch (IncorrectInputException | DomainExceptions e) {
            controller.write(e.getMessage());
        }

        List<Person> actual = PersonRepository.getAll();

        Assert.assertEquals(initial.size(), actual.size());

        for (Person person : actual) {
            if (!initial.contains(person)) {
                Assert.assertEquals(id, String.valueOf(person.getId()));
                Assert.assertEquals(name, person.getName());
                Assert.assertEquals(Sex.MALE, person.getSex());
                Assert.assertEquals(date, formatter.format(person.getBirthDate()));
            }
        }
    }

    @Test
    public void commandRemoveTest() {
        fillData();
        List<Person> initial = PersonRepository.getAll();

        String id = String.valueOf(initial.get(1).getId());
        String[] args = {"-d", id};
        try {
            controller.executeCommand(args);
        } catch (IncorrectInputException | DomainExceptions e) {
            controller.write(e.getMessage());
        }

        List<Person> actual = PersonRepository.getAll();

        Assert.assertEquals(initial.size(), actual.size());

        for (Person person : actual) {
            if (id.equals(String.valueOf(person.getId()))) {
                Assert.assertNull(person.getName());
                Assert.assertNull(person.getSex());
                Assert.assertNull(person.getBirthDate());
            }
        }
    }

    @Test
    public void commandInfoTest() {
        fillData();
        List<Person> initial = PersonRepository.getAll();

        String id = String.valueOf(initial.get(1).getId());
        String[] args = {"-i", id};
        try {
            controller.executeCommand(args);
        } catch (IncorrectInputException | DomainExceptions e) {
            controller.write(e.getMessage());
        }

        List<Person> actual = PersonRepository.getAll();

        Assert.assertEquals(initial.size(), actual.size());

        Person person;
        String actualInfo = "";
        try {
            person = PersonRepository.getById(Integer.parseInt(id));
            actualInfo = OutputBuilder.getPersonInfo(person.getId(), person.getName(), person.getSex(), person.getBirthDate());
        } catch (PersonNotFoundException e) {
            e.printStackTrace();
        }
        String expectedInfo = "Петров Петр м 03-Mar-2003";
        Assert.assertEquals(expectedInfo, actualInfo);
    }
}
