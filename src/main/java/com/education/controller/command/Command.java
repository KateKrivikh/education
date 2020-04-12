package main.java.com.education.controller.command;

import main.java.com.education.exceptions.domain.DomainExceptions;

public abstract class Command {
    protected String result;

    public abstract void execute() throws DomainExceptions;

    public String getResult() {
        return result;
    }
}
