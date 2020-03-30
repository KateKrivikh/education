package com.education.example1.commands;

import com.education.example1.InputParser;
import com.education.example1.Person;
import com.education.example1.PersonRepository;
import com.education.example1.exceptions.*;

//-d 2
public class CommandRemove extends Command {

    public static final int PARAMETERS_COUNT = 1;

    public CommandRemove(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException, PersonNotFoundException {
        InputParser.checkParametersCount(this, args);

        int id = InputParser.parseId(args[0]);

        Person person = PersonRepository.getPersonById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }

        return id;
    }
}
