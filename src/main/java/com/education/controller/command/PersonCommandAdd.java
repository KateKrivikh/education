package main.java.com.education.controller.command;

import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.util.InputParser;

public class PersonCommandAdd extends PersonCommand {

    public static final int PARAMETERS_COUNT = 3;
    public static final String MESSAGE_ADD = "Новый пользователь добавлен с идентификатором = %d";
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
        Person person = new Person(name, sex, birthDate);

        PersonRepository.save(person);

        id = person.getId();
        result = String.format(MESSAGE_ADD, id);
    }
}
