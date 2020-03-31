package com.education;

import com.education.entities.Person;
import com.education.exceptions.PersonNotFoundException;

import java.util.*;

public class PersonRepository {
    public static final String MESSAGE_NOT_FINED_PERSON_BY_ID = "Не удалось найти человека по указанному ид: %d";

    public static Map<Integer, Person> allPeople = new HashMap<>();

    public static List<Person> getAll() {
        return new ArrayList<>(allPeople.values());
    }

    public static Person getById(int id) throws PersonNotFoundException {
        if (allPeople.containsKey(id))
            return allPeople.get(id);
        else
            throw new PersonNotFoundException(String.format(MESSAGE_NOT_FINED_PERSON_BY_ID, id));
    }

    public static void save(Person person) {
        allPeople.put(person.getId(), person);
    }

    public static void clear() {
        allPeople.clear();
    }
}
