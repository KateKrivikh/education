package com.education.exceptions.domain;

/**
 * TODO I do not know yet how this class should be named.
 * This is common class for logical exceptions.
 */
public class DomainException extends RuntimeException {
    public DomainException() {
    }

    public DomainException(String message) {
        super(message);
    }

}
