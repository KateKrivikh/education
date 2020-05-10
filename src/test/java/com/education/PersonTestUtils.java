package com.education;

import com.education.entities.Person;
import com.education.entities.PersonRepository;
import com.education.entities.Sex;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class PersonTestUtils {
    private static List<Person> getAllPerson() {
        Person person1 = new Person("Иванов Иван", Sex.MALE, LocalDate.of(2000, 1, 1));
        Person person2 = new Person(null, null, null);

        return Arrays.asList(person1, person2);
    }

    public static void fillPerson() {
        List<Person> all = getAllPerson();

        PersonRepository.clear();
        for (Person person : all) {
            PersonRepository.save(person);
        }
    }

    public static int getMinPersonId() {
        return PersonRepository.getAll().stream().map(Person::getId).min(Integer::compareTo).orElse(0);
    }
}
