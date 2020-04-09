package com.education.inout;

import com.education.commands.Command;
import com.education.commands.Operation;
import com.education.entities.Sex;
import com.education.exceptions.incorrectInput.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputParser {
    // пример входных данных: -c Семенов м 24/03/1999
    public static final String PARAM_ADD = "-c";
    // пример входных данных: -u 2 Семенов м 25/02/2001
    public static final String PARAM_UPDATE = "-u";
    // пример входных данных: -d 2
    public static final String PARAM_REMOVE = "-d";
    // пример входных данных: -i 2
    public static final String PARAM_INFO = "-i";

    public static final String MESSAGE_OPERATIONS = "-c Семенов м 03/03/2003        - добавить человека\n" +
            "-u id Семенова ж 02/02/2002    - обновить данные человека по идентификатору\n" +
            "-d id                          - удалить данные человека по идентификатору\n" +
            "-i id                          - вывести информацию о человеке по идентификатору";


    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";
    public static final String DATE_FORMAT_FOR_INPUT = "dd/MM/yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_INPUT);


    public static Operation parseOperation(String operationString) {
        switch (operationString) {
            case PARAM_ADD:
                return Operation.ADD;
            case PARAM_UPDATE:
                return Operation.UPDATE;
            case PARAM_REMOVE:
                return Operation.REMOVE;
            case PARAM_INFO:
                return Operation.INFO;
        }
        throw new IncorrectOperationException(operationString);
    }

    public static void checkParametersCount(Command command, String[] args) {
        int parametersCount = command.getParametersCount();

        if (parametersCount != args.length)
            throw new IncorrectOperationParametersCount(command, parametersCount);
    }

    public static int parseId(String idString) {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new IncorrectIdException(idString);
        }
    }

    public static Sex parseSex(String sexString) {
        switch (sexString) {
            case SEX_MALE:
                return Sex.MALE;
            case SEX_FEMALE:
                return Sex.FEMALE;
        }
        // TODO Наверное, "м" и "ж" засунуть в enum.
        // TODO Следовательно, не надо будет здесь передавать.
        // TODO Соответственно при парсинге/форматировании брать из enum'a.
        throw new IncorrectSexException(sexString, SEX_MALE + ", " + SEX_FEMALE);
    }

    public static LocalDate parseDate(String birthDateString) {
        try {
            return LocalDate.parse(birthDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException(birthDateString, DATE_FORMAT_FOR_INPUT);
        }
    }

}
