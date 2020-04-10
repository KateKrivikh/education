package com.education.commands;

import com.education.exceptions.domain.DomainExceptions;

public abstract class Command {
    public abstract void execute() throws DomainExceptions;
}
