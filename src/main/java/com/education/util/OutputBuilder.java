package main.java.com.education.util;

import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.domain.DateIsEmptyException;
import main.java.com.education.exceptions.domain.SexIsEmptyException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputBuilder {
    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";

    public static final String MESSAGE_INFO_FOR_REMOVED_PERSON = "Данные о человеке с id = %s удалены!";
    public static final String MESSAGE_INFO_DELIMITER = " ";

    public static final String DATE_FORMAT_FOR_OUTPUT = "dd-MMM-yyyy";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FOR_OUTPUT, Locale.ENGLISH);


    public static String getPersonInfo(int id, String name, Sex sex, LocalDate birthDate) {
        if (name == null && sex == null && birthDate == null)
            return getPersonInfoRemoved(id);
        else
            return getPersonInfo(name, sex, birthDate);
    }

    private static String getPersonInfoRemoved(int id) {
        return String.format(MESSAGE_INFO_FOR_REMOVED_PERSON, id);
    }

    private static String getPersonInfo(String name, Sex sex, LocalDate birthDate) {
        String sexString = sex == null ? null : writeSex(sex);
        String birthDateString = birthDate == null ? null : writeDate(birthDate);

        return String.join(MESSAGE_INFO_DELIMITER, name, sexString, birthDateString);
    }

    public static String writeSex(Sex sex) throws SexIsEmptyException {
        if (sex == null)
            throw new SexIsEmptyException();

        switch (sex) {
            case MALE:
                return SEX_MALE;
            case FEMALE:
                return SEX_FEMALE;
        }

        throw new SexIsEmptyException();
    }

    public static String writeDate(LocalDate date) throws DateIsEmptyException {
        if (date == null)
            throw new DateIsEmptyException();

        return formatter.format(date);
    }
}
