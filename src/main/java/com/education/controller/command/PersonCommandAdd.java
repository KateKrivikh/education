package com.education.controller.command;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import com.education.util.InputParser;
import com.education.util.OutputBuilder;

public class PersonCommandAdd extends PersonCommand {

    public static final int PARAMETERS_COUNT = 3;
    public static final String MESSAGE_ADD_EXCEPTION = "Ошибка при операции ADD";

    public PersonCommandAdd() {
        super(Operation.ADD, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        try {
            InputParser.checkParametersCount(getParametersCount(), parameters);

            name = parameters[0];
            sex = InputParser.parseSex(parameters[1]);
            birthDate = InputParser.parseDate(parameters[2]);
        } catch (IncorrectInputException e) {
            throw new IncorrectInputException(MESSAGE_ADD_EXCEPTION, e);
        }
    }

    @Override
    public void execute() {
        Person person = Person.create(name, sex, birthDate);

        PersonRepository.save(person);

        id = person.getId();
        result = OutputBuilder.getPersonAddedMessage(id);
    }
}
