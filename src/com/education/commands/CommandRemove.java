package com.education.commands;

import com.education.PersonRepository;
import com.education.entities.Person;
import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.incorrectInput.IncorrectInputException;
import com.education.inout.InputParser;

public class CommandRemove extends CommandPerson {

    public static final int PARAMETERS_COUNT = 1;

    public CommandRemove(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        InputParser.checkParametersCount(this, parameters);
        id = InputParser.parseId(parameters[0]);
    }

    @Override
    public void execute() throws DomainExceptions {
        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }
    }
}
