package test.java.com.education;

import main.java.com.education.entities.Person;
import main.java.com.education.entities.PersonRepository;
import main.java.com.education.entities.Sex;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class UtilTest {
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
