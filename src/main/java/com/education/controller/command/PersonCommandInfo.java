package com.education.controller.command;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.exceptions.domain.DomainException;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;
import com.education.util.OutputBuilder;

public class PersonCommandInfo extends PersonCommand {

    public static final int PARAMETERS_COUNT = 1;
    public static final String MESSAGE_INFO_EXCEPTION = "Ошибка при операции INFO";

    public PersonCommandInfo() {
        super(Operation.INFO, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        try {
            InputParser.checkParametersCount(getParametersCount(), parameters);
            id = InputParser.parseId(parameters[0]);
        } catch (IncorrectInputException e) {
            throw new IncorrectInputException(MESSAGE_INFO_EXCEPTION, e);
        }
    }

    @Override
    public void execute() throws DomainException {
        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            name = person.getName();
            sex = person.getSex();
            birthDate = person.getBirthDate();
        }

        result = OutputBuilder.getPersonInfo(id, name, sex, birthDate);
    }
}
