package com.education.commands;

import com.education.PersonRepository;
import com.education.entities.Person;
import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.incorrectInput.IncorrectInputException;
import com.education.inout.InputParser;

public class CommandRemove extends Command {

    public static final int PARAMETERS_COUNT = 1;

    public CommandRemove(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException, DomainExceptions {
        InputParser.checkParametersCount(this, args);

        int id = InputParser.parseId(args[0]);

        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }

        return id;
    }
}
