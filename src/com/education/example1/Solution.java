package com.education.example1;

import com.sun.istack.internal.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";
    public static final String DATE_FORMAT_FOR_INPUT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_FOR_OUTPUT = "dd-MMM-yyyy";

    public static final String PARAM_ADD = "-c";
    public static final String PARAM_UPDATE = "-u";
    public static final String PARAM_REMOVE = "-d";
    public static final String PARAM_INFO = "-i";

    public static final String MESSAGE_NOT_ENOUGH_PARAMETERS = "Недостаточно параметров!";
    public static final String MESSAGE_PARSE_EXCEPTION_SEX = "Не удалось определить пол человека по параметру %s\nОжидаемый формат: \"%s\" или \"%s\"";
    public static final String MESSAGE_PARSE_EXCEPTION_DATE = "Не удалось определить дату рождения по параметру %s\nОжидаемый формат: %s";
    public static final String MESSAGE_PARSE_EXCEPTION_ID = "Не удалось определить идентификатор человека по параметру %s\nОжидаемый формат - целое число";
    public static final String MESSAGE_NOT_FINED_PERSON_BY_ID = "Не удалось найти человека по указанному ид: %d";
    public static final String MESSAGE_INFO_FOR_REMOVED_PERSON = "Данные о человеке с id=%s удалены!";

    public static List<Person> allPeople = new ArrayList<>();

    static {
        // По заданию в main должны передаваться параметры CRUD.
        // Список при этом должен быть заполнен, чтобы были какие-то данные.
        // Надо подумать и сделать поинтересней.
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=2
        try {
            removePerson("2");
        } catch (ParseException e) {
            //
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Запускать код нужно с параметрами!");
            System.exit(-1);
        }

        try {
            switch (args[0]) {
                case PARAM_ADD: {
                    int id = addPerson(args[1], args[2], args[3]);
                    System.out.println(id);
                    break;
                }
                case PARAM_UPDATE:
                    updatePerson(args[1], args[2], args[3], args[4]);
                    break;
                case PARAM_REMOVE:
                    removePerson(args[1]);
                    break;
                case PARAM_INFO:
                    String info = getPersonInfo(args[1]);
                    System.out.println(info);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_NOT_ENOUGH_PARAMETERS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static String getPersonInfo(String idString) throws ParseException, NullPointerException {
        Person person = findPerson(idString);

        String name;
        Sex sex;
        Date birthday;

        synchronized (person) {
            name = person.getName();
            sex = person.getSex();
            birthday = person.getBirthDate();
        }

        if (name == null && sex == null && birthday == null)
            return String.format(MESSAGE_INFO_FOR_REMOVED_PERSON, idString);

        return String.join(" ", name, getSexStringForDisplay(sex), getBirthdayForDisplay(birthday));
    }

    private static void removePerson(String idString) throws ParseException, NullPointerException {
        Person person = findPerson(idString);

        synchronized (person) {
            person.setName(null);
            person.setSex(null);
            person.setBirthDate(null);
        }
    }

    private static void updatePerson(String idString, String name, String sexString, String birthdayString)
            throws ParseException, NullPointerException {
        Person person = findPerson(idString);

        Sex sex = getSexFromDisplayingString(sexString);
        Date birthday = getBirthdayFromDisplayingString(birthdayString);

        synchronized (person) {
            person.setName(name);
            person.setSex(sex);
            person.setBirthDate(birthday);
        }
    }

    private static int addPerson(String name, String sexString, String birthdayString) throws ParseException {
        Date birthday = getBirthdayFromDisplayingString(birthdayString);
        Sex sex = getSexFromDisplayingString(sexString);

        Person person;
        if (sex.equals(Sex.MALE))
            person = Person.createMale(name, birthday);
        else
            person = Person.createFemale(name, birthday);

        allPeople.add(person);

        return allPeople.indexOf(person);
    }

    private static Person findPerson(String idString) throws ParseException, NullPointerException {
        int id = getIdFromDisplayingString(idString);
        return getPersonById(id);
    }

    private static Person getPersonById(int id) throws NullPointerException {
        if (allPeople.size() > id)
            return allPeople.get(id);
        else
            throw new NullPointerException(String.format(MESSAGE_NOT_FINED_PERSON_BY_ID, id));
    }


    private static int getIdFromDisplayingString(String idString) throws ParseException {
        try {
            return Integer.parseInt(idString);
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(MESSAGE_PARSE_EXCEPTION_ID, idString), 0);
        }
    }

    private static Date getBirthdayFromDisplayingString(String birthdayString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_INPUT);
        try {
            return dateFormat.parse(birthdayString);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_PARSE_EXCEPTION_DATE, birthdayString, DATE_FORMAT_FOR_INPUT), 0);
        }
    }

    private static String getBirthdayForDisplay(Date birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_OUTPUT, Locale.ENGLISH);
        return dateFormat.format(birthday);
    }

    private static Sex getSexFromDisplayingString(String sexString) throws ParseException {
        switch (sexString) {
            case SEX_MALE:
                return Sex.MALE;
            case SEX_FEMALE:
                return Sex.FEMALE;
            default:
                throw new ParseException(String.format(MESSAGE_PARSE_EXCEPTION_SEX, sexString, SEX_MALE, SEX_FEMALE), 0);
        }
    }

    private static String getSexStringForDisplay(@Nullable Sex sex) {
        switch (sex) {
            case MALE:
                return SEX_MALE;
            case FEMALE:
                return SEX_FEMALE;
        }
        return "";
    }
}
