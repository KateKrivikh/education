package main.java.com.education.exceptions.domain;

public class PersonNotFoundException extends DomainExceptions {
    public static final String MESSAGE_NOT_FINED_PERSON_BY_ID = "Не удалось найти человека по указанному ид: %d";

    public PersonNotFoundException(int id) {
        super(String.format(MESSAGE_NOT_FINED_PERSON_BY_ID, id));
    }
}
