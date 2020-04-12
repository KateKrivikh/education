package main.java.com.education.controller.command;

import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;

public class PersonCommandUpdate extends PersonCommand {

    public static final int PARAMETERS_COUNT = 4;

    public PersonCommandUpdate() {
        super(Operation.UPDATE, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        InputParser.checkParametersCount(getParametersCount(), parameters);

        id = InputParser.parseId(parameters[0]);
        name = parameters[1];
        sex = InputParser.parseSex(parameters[2]);
        birthDate = InputParser.parseDate(parameters[3]);
    }

    @Override
    public void execute() throws DomainExceptions {
        Person person = PersonRepository.getById(id);

        // TODO пока что оставила как есть, буду думать дальше
        synchronized (person) {
            person.setName(name);
            person.setSex(sex);
            person.setBirthDate(birthDate);
        }
    }
}