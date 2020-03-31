package com.education.commands;

import com.education.*;
import com.education.entities.Person;
import com.education.entities.Sex;
import com.education.exceptions.IncorrectInputException;
import com.education.exceptions.PersonNotFoundException;
import com.education.inout.ConsoleWriter;
import com.education.inout.InputParser;
import com.education.inout.OutputBuilder;

import java.util.Date;

public class CommandInfo extends Command {

    public static final int PARAMETERS_COUNT = 1;

    public CommandInfo(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException, PersonNotFoundException {
        InputParser.checkParametersCount(this, args);

        int id = InputParser.parseId(args[0]);

        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        String name;
        Sex sex;
        Date birthday;

        synchronized (person) {
            name = person.getName();
            sex = person.getSex();
            birthday = person.getBirthDate();
        }

        String info = OutputBuilder.getPersonInfo(id, name, sex, birthday);

        ConsoleWriter.write(info);

        return id;
    }
}
