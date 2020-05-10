package com.education.exceptions.domain;

import java.util.Objects;

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


    @Override
    public int hashCode() {
        return Objects.hash(getMessage());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainException exception = (DomainException) o;
        return getMessage() == null ? exception.getMessage() == null : getMessage().equals(exception.getMessage());
    }
}
