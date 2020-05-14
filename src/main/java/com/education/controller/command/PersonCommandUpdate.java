package com.education.controller.command;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.exceptions.domain.DomainException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;

public class PersonCommandUpdate extends PersonCommand {

    public static final int PARAMETERS_COUNT = 4;
    public static final String MESSAGE_UPDATE_EXCEPTION = "Ошибка при операции UPDATE";

    public PersonCommandUpdate() {
        super(Operation.UPDATE, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        try {
            InputParser.checkParametersCount(getParametersCount(), parameters);

            id = InputParser.parseId(parameters[0]);
            name = parameters[1];
            sex = InputParser.parseSex(parameters[2]);
            birthDate = InputParser.parseDate(parameters[3]);
        } catch (IncorrectInputException e) {
            throw new IncorrectInputException(MESSAGE_UPDATE_EXCEPTION, e);
        }
    }

    @Override
    public void execute() throws DomainException {
        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            person.update(name, sex, birthDate);
        }
    }
}
