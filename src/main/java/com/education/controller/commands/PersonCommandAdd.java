package main.java.com.education.controller.commands;

import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;

public class PersonCommandAdd extends PersonCommand {

    public static final int PARAMETERS_COUNT = 3;
    public static final String MESSAGE_ADD = "Новый пользователь добавлен с идентификатором = ";

    public PersonCommandAdd(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        InputParser.checkParametersCount(getParametersCount(), parameters);

        name = parameters[0];
        sex = InputParser.parseSex(parameters[1]);
        birthDate = InputParser.parseDate(parameters[2]);
    }

    @Override
    public void execute() {
        Person person;
        // TODO либо добавить дополнительные поля для мужчин и для женщин и сделать фабрику
        // TODO либо один конструктор
        if (sex.equals(Sex.MALE))
            person = Person.createMale(name, birthDate);
        else
            person = Person.createFemale(name, birthDate);

        PersonRepository.save(person);

        id = person.getId();

        result = MESSAGE_ADD + id;
    }
}
