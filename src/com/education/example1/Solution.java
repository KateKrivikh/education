package com.education.example1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Solution {
    public static final String SEX_MALE = "м";
    public static final String SEX_FEMALE = "ж";

    public static List<Person> allPeople = new ArrayList<>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Запускать код нужно с параметрами!");
            System.exit(-1);
        }

        try {
            switch (args[0]) {
                case "-c": {
                    int id = addPerson(args[1], args[2], args[3]);
                    System.out.println(id);
                    break;
                }
                case "-u":
                    updatePerson(args[1], args[2], args[3], args[4]);
                    break;
                case "-d":
                    removePerson(args[1]);
                    break;
                case "-i":
                    String info = getPersonInfo(args[1]);
                    System.out.println(info);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Недостаточно параметров!");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
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
            return "Данные о человеке удалены!";

        return String.join(" ", name, getSexForDisplay(sex), getBirthdayForDisplay(birthday));
    }

    private static String getBirthdayForDisplay(Date birthday) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        return dateFormat.format(birthday);
    }

    private static String getSexForDisplay(Sex sex) {
        if (Objects.equals(sex, Sex.MALE))
            return SEX_MALE;
        else if (Objects.equals(sex, Sex.FEMALE))
            return SEX_FEMALE;
        return "";
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

        Sex sex = parseSex(sexString);
        Date birthday = parseDate(birthdayString);

        synchronized (person) {
            person.setName(name);
            person.setSex(sex);
            person.setBirthDate(birthday);
        }
    }

    private static int addPerson(String name, String sexString, String birthdayString) throws ParseException {
        Date birthday = parseDate(birthdayString);
        Sex sex = parseSex(sexString);

        Person person;
        if (sex.equals(Sex.MALE))
            person = Person.createMale(name, birthday);
        else
            person = Person.createFemale(name, birthday);

        int id;
        synchronized (allPeople) {
            allPeople.add(person);
            id = allPeople.size() - 1;
        }
        return id;
    }

    private static Date parseDate(String birthdayString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(birthdayString);
        } catch (ParseException e) {
            throw new ParseException("Некорректно указана дата рождения! Ошибка в символе " + (e.getErrorOffset() + 1) +
                    " строки " + birthdayString, e.getErrorOffset());
        }
    }

    private static Sex parseSex(String sexString) throws ParseException {
        if (SEX_MALE.equals(sexString))
            return Sex.MALE;
        else if (SEX_FEMALE.equals(sexString))
            return Sex.FEMALE;
        else
            throw new ParseException("Не определен пол человека: " + sexString, 0);
    }

    private static Person findPerson(String idString) throws ParseException, NullPointerException {
        Person person;
        try {// TODO null
            person = allPeople.get(Integer.parseInt(idString));
        } catch (NumberFormatException e) {
            throw new ParseException("Некорректный ид: " + idString, 0);
        } catch (IndexOutOfBoundsException e) {
            throw new NullPointerException("Нет человека по указанному ид: " + idString);
        }
        return person;
    }
}
