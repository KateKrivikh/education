package main.java.com.education.controller.command;

import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;
import main.java.com.education.util.OutputBuilder;

public class PersonCommandInfo extends PersonCommand {

    public static final int PARAMETERS_COUNT = 1;

    public PersonCommandInfo(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public void setParameters(String... parameters) throws IncorrectInputException {
        InputParser.checkParametersCount(getParametersCount(), parameters);
        id = InputParser.parseId(parameters[0]);
    }

    @Override
    public void execute() throws DomainExceptions {
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
