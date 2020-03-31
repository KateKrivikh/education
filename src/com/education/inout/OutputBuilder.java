package com.education.inout;

import com.education.entities.Sex;
import com.sun.istack.internal.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OutputBuilder {
    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";
    public static final String SEX_UNKNOWN = "";

    public static final String MESSAGE_INFO_FOR_REMOVED_PERSON = "Данные о человеке с id = %s удалены!";
    public static final String MESSAGE_INFO_DELIMITER = " ";

    public static final String DATE_FORMAT_FOR_OUTPUT = "dd-MMM-yyyy";


    public static String getPersonInfo(int id, String name, Sex sex, Date birthday) {
        if (name == null && sex == null && birthday == null)
            return getPersonInfoRemoved(id);
        else
            return getPersonInfo(name, sex, birthday);
    }

    private static String getPersonInfoRemoved(int id) {
        return String.format(MESSAGE_INFO_FOR_REMOVED_PERSON, id);
    }

    private static String getPersonInfo(String name, Sex sex, Date birthday) {
        return String.join(MESSAGE_INFO_DELIMITER, name, writeSex(sex), writeDate(birthday));
    }

    private static String writeSex(@Nullable Sex sex) {
        switch (sex) {
            case MALE: return SEX_MALE;
            case FEMALE: return SEX_FEMALE;
        }
        return SEX_UNKNOWN;
    }

    private static String writeDate(Date birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_OUTPUT, Locale.ENGLISH);
        return dateFormat.format(birthday);
    }
}
