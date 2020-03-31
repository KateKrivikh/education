package com.education.commands;

import com.education.inout.InputParser;
import com.education.entities.Person;
import com.education.PersonRepository;
import com.education.entities.Sex;
import com.education.exceptions.IncorrectInputException;

import java.util.Date;

public class CommandAdd extends Command {

    public static final int PARAMETERS_COUNT = 3;

    public CommandAdd(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException {
        InputParser.checkParametersCount(this, args);

        String name = args[0];
        Sex sex = InputParser.parseSex(args[1]);
        Date birthday = InputParser.parseDate(args[2]);

        Person person;
        // TODO либо добавить дополнительные поля для мужчин и для женщин и сделать фабрику
        // TODO либо один конструктор
        if (sex.equals(Sex.MALE))
            person = Person.createMale(name, birthday);
        else
            person = Person.createFemale(name, birthday);

        PersonRepository.save(person);

        return person.getId();
    }
}
