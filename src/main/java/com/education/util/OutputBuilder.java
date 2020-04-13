package main.java.com.education.util;

import main.java.com.education.entities.Sex;
import com.sun.istack.internal.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputBuilder {
    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";
    public static final String SEX_UNKNOWN = "";

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
        return String.join(MESSAGE_INFO_DELIMITER, name, writeSex(sex), writeDate(birthDate));
    }

    public static String writeSex(@Nullable Sex sex) {
        switch (sex) {
            case MALE:
                return SEX_MALE;
            case FEMALE:
                return SEX_FEMALE;
        }
        return SEX_UNKNOWN;
    }

    public static String writeDate(LocalDate birthDate) {
        return formatter.format(birthDate);
    }
}
