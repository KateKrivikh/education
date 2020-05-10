package com.education.controller.command;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;

public class PersonCommandRemove extends PersonCommand {

    public static final int PARAMETERS_COUNT = 1;
    public static final String MESSAGE_REMOVE_EXCEPTION = "Ошибка при операции REMOVE";

    public PersonCommandRemove() {
        super(Operation.REMOVE, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        try {
            InputParser.checkParametersCount(getParametersCount(), parameters);
            id = InputParser.parseId(parameters[0]);
        } catch (IncorrectInputException e) {
            throw new IncorrectInputException(MESSAGE_REMOVE_EXCEPTION, e);
        }
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
