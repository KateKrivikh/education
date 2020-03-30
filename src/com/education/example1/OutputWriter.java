package com.education.example1;

import com.education.example1.Sex;
import com.sun.istack.internal.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OutputWriter {
    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";
    public static final String SEX_UNKNOWN = "";

    public static final String MESSAGE_INFO_FOR_REMOVED_PERSON = "Данные о человеке с id=%s удалены!";
    public static final String MESSAGE_INFO_DELIMITER = " ";

    public static final String DATE_FORMAT_FOR_OUTPUT = "dd-MMM-yyyy";


    public static void writeInfo(String string) {
        System.out.println(string);
    }

    public static void writeInfoPersonRemoved(int id) {
        writeInfo(String.format(MESSAGE_INFO_FOR_REMOVED_PERSON, id));
    }

    public static void writeInfoPerson(String name, Sex sex, Date birthday) {
        writeInfo(String.join(MESSAGE_INFO_DELIMITER, name, writeSex(sex), writeDate(birthday)));
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
