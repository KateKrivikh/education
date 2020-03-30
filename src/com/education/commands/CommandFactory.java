package com.education.commands;

public class CommandFactory {

    public static Command create(Operation operation) {
        switch (operation) {
            case ADD: return new CommandAdd(operation);
            case UPDATE: return new CommandUpdate(operation);
            case REMOVE: return new CommandRemove(operation);
            case INFO: return new CommandInfo(operation);
        }
        return null;
    }
}
