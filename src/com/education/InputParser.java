package com.education;

import com.education.commands.Command;
import com.education.commands.Operation;
import com.education.exceptions.IncorrectInputException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InputParser {
    // пример входных данных: -c Сидоров м 24/03/1999
    public static final String PARAM_ADD = "-c";
    // пример входных данных: -u 2 Сидорова ж 25/02/2001
    public static final String PARAM_UPDATE = "-u";
    // пример входных данных: -d 2
    public static final String PARAM_REMOVE = "-d";
    // пример входных данных: -i 2
    public static final String PARAM_INFO = "-i";


    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";
    public static final String DATE_FORMAT_FOR_INPUT = "dd/MM/yyyy";

    public static final String MESSAGE_PARSE_EXCEPTION_UNKNOWN_COMMAND = "Неизвестная команда: ";
    public static final String MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT = "Неверное количество параметров для команды %s\nОжидаемое количество параметров: %d";
    public static final String MESSAGE_PARSE_EXCEPTION_SEX = "Не удалось определить пол человека по параметру %s\nОжидаемый формат: \"%s\" или \"%s\"";
    public static final String MESSAGE_PARSE_EXCEPTION_DATE = "Не удалось определить дату рождения по параметру %s\nОжидаемый формат: %s";
    public static final String MESSAGE_PARSE_EXCEPTION_ID = "Не удалось определить идентификатор человека по параметру %s\nОжидаемый формат - целое число";


    public static Operation parseOperation(String operationString) throws IncorrectInputException {
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
        throw new IncorrectInputException(MESSAGE_PARSE_EXCEPTION_UNKNOWN_COMMAND + operationString);
    }

    public static void checkParametersCount(Command command, String[] args) throws IncorrectInputException {
        int parametersCount = command.getParametersCount();

        if (parametersCount != args.length)
            throw new IncorrectInputException(String.format(MESSAGE_PARSE_EXCEPTION_WRONG_PARAMETERS_COUNT,
                    command.getOperation(), parametersCount));
    }

    public static int parseId(String idString) throws IncorrectInputException {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException(String.format(MESSAGE_PARSE_EXCEPTION_ID, idString));
        }
    }

    public static Sex parseSex(String sexString) throws IncorrectInputException {
        switch (sexString) {
            case SEX_MALE:
                return Sex.MALE;
            case SEX_FEMALE:
                return Sex.FEMALE;
        }
        throw new IncorrectInputException(String.format(MESSAGE_PARSE_EXCEPTION_SEX, sexString, SEX_MALE, SEX_FEMALE));
    }

    public static Date parseDate(String birthdayString) throws IncorrectInputException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_INPUT);
        try {
            return dateFormat.parse(birthdayString);
        } catch (ParseException e) {
            throw new IncorrectInputException(String.format(MESSAGE_PARSE_EXCEPTION_DATE, birthdayString, DATE_FORMAT_FOR_INPUT));
        }
    }

}
