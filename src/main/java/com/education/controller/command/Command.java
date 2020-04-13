package main.java.com.education.controller.command;

import main.java.com.education.exceptions.domain.DomainExceptions;

/**
 * Command, which can be executed in this system.
 * Command depends on CRUD-operation, but also could be independent.
 * It can be executed and could have result.
 */
public abstract class Command {
    protected String result;

    public abstract void execute() throws DomainExceptions;

    public String getResult() {
        return result;
    }
}
