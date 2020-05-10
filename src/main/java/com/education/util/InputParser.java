package com.education.util;

import com.education.controller.command.Operation;
import com.education.entities.Sex;
import com.education.exceptions.inout.incorrectInput.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputParser {
    public static final String PARAM_NAME_OPERATION = "operation";
    public static final String PARAM_NAME_ID = "id";
    public static final String PARAM_NAME_SEX = "sex";
    public static final String PARAM_NAME_DATE = "date";

    public static final String PARAM_OPERATION_ADD = "-c";
    public static final String PARAM_OPERATION_UPDATE = "-u";
    public static final String PARAM_OPERATION_REMOVE = "-d";
    public static final String PARAM_OPERATION_INFO = "-i";

    public static final String PARAM_SEX_MALE = "м";
    public static final String PARAM_SEX_FEMALE = "ж";
    public static final String SEX_FORMATS_FOR_MESSAGE = PARAM_SEX_MALE + ", " + PARAM_SEX_FEMALE;

    public static final String MESSAGE_OPERATIONS = "-c Семенов м 03/03/2003        - добавить человека\n" +
            "-u id Семенова ж 02/02/2002    - обновить данные человека по идентификатору\n" +
            "-d id                          - удалить данные человека по идентификатору\n" +
            "-i id                          - вывести информацию о человеке по идентификатору";


    public static final String DATE_FORMAT_FOR_INPUT = "dd/MM/yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_INPUT);


    public static void checkParametersCount(int parametersCount, String... parameters) throws IncorrectOperationParametersCountException {
        if (parametersCount != parameters.length)
            throw new IncorrectOperationParametersCountException(parametersCount);
    }

    // TODO ignoreCase?..
    public static Operation parseOperation(String operationString) throws EmptyParameterException, IncorrectOperationException {
        checkNull(operationString, PARAM_NAME_OPERATION);

        switch (operationString) {
            case PARAM_OPERATION_ADD:
                return Operation.ADD;
            case PARAM_OPERATION_UPDATE:
                return Operation.UPDATE;
            case PARAM_OPERATION_REMOVE:
                return Operation.REMOVE;
            case PARAM_OPERATION_INFO:
                return Operation.INFO;
        }
        throw new IncorrectOperationException(operationString);
    }

    public static int parseId(String idString) throws EmptyParameterException, IncorrectIdException {
        checkNull(idString, PARAM_NAME_ID);

        try {
            int id = Integer.parseInt(idString);
            if (id < 1)
                throw new IncorrectIdException(idString);
            return id;
        } catch (NumberFormatException e) {
            throw new IncorrectIdException(idString);
        }
    }

    // TODO ignoreCase?..
    public static Sex parseSex(String sexString) throws EmptyParameterException, IncorrectSexException {
        checkNull(sexString, PARAM_NAME_SEX);

        switch (sexString) {
            case PARAM_SEX_MALE:
                return Sex.MALE;
            case PARAM_SEX_FEMALE:
                return Sex.FEMALE;
        }
        throw new IncorrectSexException(sexString, SEX_FORMATS_FOR_MESSAGE);
    }

    public static LocalDate parseDate(String birthDateString) throws EmptyParameterException, IncorrectDateException {
        checkNull(birthDateString, PARAM_NAME_DATE);

        try {
            return LocalDate.parse(birthDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IncorrectDateException(birthDateString, DATE_FORMAT_FOR_INPUT);
        }
    }


    private static void checkNull(String checkedParameter, String parameterName) throws EmptyParameterException {
        if (checkedParameter == null || checkedParameter.trim().isEmpty())
            throw new EmptyParameterException(parameterName);
    }
}
