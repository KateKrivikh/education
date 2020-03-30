package com.education.commands;

import com.education.*;
import com.education.exceptions.IncorrectInputException;
import com.education.exceptions.PersonNotFoundException;

import java.util.Date;

//-i 2
public class CommandInfo extends Command {

    public static final int PARAMETERS_COUNT = 1;

    public CommandInfo(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException, PersonNotFoundException {
        InputParser.checkParametersCount(this, args);

        int id = InputParser.parseId(args[0]);

        Person person = PersonRepository.getPersonById(id);

        // TODO пока что оставила как есть, буду думать дальше
        String name;
        Sex sex;
        Date birthday;

        synchronized (person) {
            name = person.getName();
            sex = person.getSex();
            birthday = person.getBirthDate();
        }

        if (name == null && sex == null && birthday == null)
            OutputWriter.writeInfoPersonRemoved(id);
        else
            OutputWriter.writeInfoPerson(name, sex, birthday);

        return id;
    }
}
