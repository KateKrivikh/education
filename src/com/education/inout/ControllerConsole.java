package com.education.inout;

import com.education.commands.Command;
import com.education.commands.CommandPerson;
import com.education.commands.Operation;
import com.education.exceptions.console.ExitExpectedException;
import com.education.exceptions.console.ReadFromConsoleException;
import com.education.exceptions.incorrectInput.IncorrectInputException;
import com.education.exceptions.incorrectInput.IncorrectOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerConsole extends Controller {
    public static final String COMMAND_QUIT = "-q";
    public static final String COMMAND_HELP = "-h";
    public static final String MESSAGE_HELP = InputParser.MESSAGE_OPERATIONS + "\n" +
            "-q                             - выход\n" +
            "-h                             - помощь";
    public static final String MESSAGE_ADD = "Новый пользователь добавлен с идентификатором = ";
    public static final String MESSAGE_EXCEPTION_EXPECTING_COMMAND = "Ошибка при чтении команды из консоли";

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ControllerConsole() {
        write(MESSAGE_HELP);
    }

    @Override
    public String getCommandString() throws ReadFromConsoleException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromConsoleException(MESSAGE_EXCEPTION_EXPECTING_COMMAND);
        }
    }

    @Override
    public Command parseCommand(String commandString) throws IncorrectInputException, ExitExpectedException {
        try {
            return super.parseCommand(commandString);
        } catch (IncorrectOperationException e) {
            if (isExit(commandString)) {
                return this.new ConsoleCommandQuit();
            } else if (isHelp(commandString)) {
                return this.new ConsoleCommandHelp();
            } else
                throw e;
        }
    }

    @Override
    public void actionsAfterCommand(Command command) {
        if (command instanceof CommandPerson && ((CommandPerson) command).getOperation().equals(Operation.ADD))
            write(MESSAGE_ADD + ((CommandPerson) command).getId());
    }

    @Override
    public void write(String info) {
        System.out.println(info);
    }



    private boolean isHelp(String operationString) {
        return COMMAND_HELP.equals(operationString);
    }

    private boolean isExit(String commandString) {
        return COMMAND_QUIT.equals(commandString);
    }

    private void beforeExit() {
        try {
            reader.close();
        } catch (IOException ignored) {
        }
    }


    public class ConsoleCommandQuit extends Command {
        @Override
        public void execute() throws ExitExpectedException {
            beforeExit();
            throw new ExitExpectedException();
        }
    }

    public class ConsoleCommandHelp extends Command {
        @Override
        public void execute() {
            write(MESSAGE_HELP);
        }
    }
}
