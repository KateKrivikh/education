package com.education.commands;

import com.education.PersonRepository;
import com.education.entities.Person;
import com.education.entities.Sex;
import com.education.exceptions.incorrectInput.IncorrectInputException;
import com.education.inout.InputParser;

public class CommandAdd extends CommandPerson {

    public static final int PARAMETERS_COUNT = 3;

    public CommandAdd(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        InputParser.checkParametersCount(this, parameters);

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
    }
}
