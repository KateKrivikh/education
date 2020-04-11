package main.java.com.education.entities;

import main.java.com.education.exceptions.domain.PersonNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonRepository {

    public static Map<Integer, Person> allPeople = new HashMap<>();

    public static List<Person> getAll() {
        return new ArrayList<>(allPeople.values());
    }

    public static Person getById(int id) throws PersonNotFoundException {
        if (allPeople.containsKey(id))
            return allPeople.get(id);
        else
            throw new PersonNotFoundException(id);
    }

    public static void save(Person person) {
        allPeople.put(person.getId(), person);
    }

    public static void clear() {
        allPeople.clear();
    }
}
