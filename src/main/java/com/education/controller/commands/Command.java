package main.java.com.education.controller.commands;

import main.java.com.education.exceptions.domain.DomainExceptions;

public interface Command {
    void execute() throws DomainExceptions;
}
