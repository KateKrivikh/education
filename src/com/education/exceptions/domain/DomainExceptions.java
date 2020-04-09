package com.education.exceptions.domain;

/**
 * TODO I do not know yet how this class should be named.
 * This is common class for logical exceptions.
 */
public class DomainExceptions extends RuntimeException {
    public DomainExceptions() {
    }

    public DomainExceptions(String message) {
        super(message);
    }
}
