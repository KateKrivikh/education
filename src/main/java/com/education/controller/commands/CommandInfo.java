package main.java.com.education.controller.commands;

import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.Start;
import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;
import main.java.com.education.util.OutputBuilder;

import java.time.LocalDate;

public class CommandInfo extends CommandPerson {

    public static final int PARAMETERS_COUNT = 1;

    public CommandInfo(Operation operation) {
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
        String name;
        Sex sex;
        LocalDate birthDate;

        synchronized (person) {
            name = person.getName();
            sex = person.getSex();
            birthDate = person.getBirthDate();
        }

        String info = OutputBuilder.getPersonInfo(id, name, sex, birthDate);

        Start.controller.write(info);
    }
}
