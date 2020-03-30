package com.education;

import com.education.exceptions.PersonNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// TODO Не очень поняла, что такое репозиторий, но думаю, что это будет
// TODO класс для непосредственной работы с хранимыми данными.
// TODO Пока что просто вынесла сюда работу с allPeople
public class PersonRepository {
    public static final String MESSAGE_NOT_FINED_PERSON_BY_ID = "Не удалось найти человека по указанному ид: %d";

    public static List<Person> allPeople = new ArrayList<>();

    static {
        // По заданию в main должны передаваться параметры CRUD.
        // Список при этом должен быть заполнен, чтобы были какие-то данные.
        // Надо подумать и сделать поинтересней.
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
        allPeople.add(Person.createMale("Сергеев Сергей", new Date()));  //сегодня родился    id=2
    }


    public static Person getPersonById(int id) throws PersonNotFoundException {
        // TODO allPeople
        if (allPeople.size() > id)
            return allPeople.get(id);
        else
            throw new PersonNotFoundException(String.format(MESSAGE_NOT_FINED_PERSON_BY_ID, id));
    }

    public static int addPerson(Person person) {
        // TODO allPeople
        allPeople.add(person);

        return allPeople.indexOf(person);
    }
}
