package main.java.com.education.entities;

import java.time.LocalDate;

public class Person {
    private static int countPerson = 0;

    // Почему int?..
    // UUID - не считаю нужным, т.к. изначально задача ориентирована на ввод человеком id вручную. UUID вводить неудобно.
    // Long - лень. Сейчас ещё не ясна цель этого проекта, поэтому вполне могу перейти на UUID. Изначально был int, менять не стала.
    // Короче, нужно определять благородную цель задачи, тогда появится определенность с типом.
    private int id;
    private String name;
    private Sex sex;
    private LocalDate birthDate;

    private Person(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.id = ++countPerson;
    }

    public static Person createMale(String name, LocalDate birthDate) {
        return new Person(name, Sex.MALE, birthDate);
    }

    public static Person createFemale(String name, LocalDate birthDate) {
        return new Person(name, Sex.FEMALE, birthDate);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
