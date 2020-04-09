package com.education.commands;

import com.education.PersonRepository;
import com.education.Start;
import com.education.entities.Person;
import com.education.entities.Sex;
import com.education.exceptions.domain.DomainExceptions;
import com.education.exceptions.incorrectInput.IncorrectInputException;
import com.education.inout.InputParser;
import com.education.inout.OutputBuilder;

import java.time.LocalDate;

public class CommandInfo extends Command {

    public static final int PARAMETERS_COUNT = 1;

    public CommandInfo(Operation operation) {
        super(operation, PARAMETERS_COUNT);
    }

    @Override
    public int execute(String[] args) throws IncorrectInputException, DomainExceptions {
        InputParser.checkParametersCount(this, args);

        int id = InputParser.parseId(args[0]);

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

        return id;
    }
}
