package main.java.com.education.controller.commands;

import main.java.com.education.exceptions.domain.DomainExceptions;

public abstract class Command {
    public abstract void execute() throws DomainExceptions;
}
