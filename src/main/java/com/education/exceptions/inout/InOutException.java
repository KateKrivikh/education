package main.java.com.education.exceptions.inout;

import java.util.Objects;

/**
 * Common class for exceptions of in/out
 */
public class InOutException extends RuntimeException {
    public InOutException() {
        super();
    }

    public InOutException(String message) {
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
        InOutException exception = (InOutException) o;
        return getMessage() == null ? exception.getMessage() == null : getMessage().equals(exception.getMessage());
    }
}
