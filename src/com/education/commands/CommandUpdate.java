package com.education.commands;

import com.education.inout.InputParser;
import com.education.entities.Person;
import com.education.PersonRepository;
import com.education.entities.Sex;
import com.education.exceptions.IncorrectInputException;
import com.education.exceptions.PersonNotFoundException;

import java.util.Date;

public class CommandUpdate extends Command {

    public static final int PARAMETERS_COUNT = 4;

    public CommandUpdate(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException, PersonNotFoundException {
        InputParser.checkParametersCount(this, args);

        int id = InputParser.parseId(args[0]);
        String name = args[1];
        Sex sex = InputParser.parseSex(args[2]);
        Date birthday = InputParser.parseDate(args[3]);

        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            person.setName(name);
            person.setSex(sex);
            person.setBirthDate(birthday);
        }

        return id;
    }
}
