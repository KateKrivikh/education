package com.education.entities;

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

    private Person() {
        this.id = ++countPerson;
    }

    public static Person create(String name, Sex sex, LocalDate birthDate) {
        Person person = new Person();
        person.update(name, sex, birthDate);
        return person;
    }

    public void update(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public void remove() {
        name = null;
        sex = null;
        birthDate = null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
