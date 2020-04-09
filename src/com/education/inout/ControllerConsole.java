package com.education.inout;

import com.education.commands.Operation;
import com.education.exceptions.console.ExitExpectedException;
import com.education.exceptions.console.ReadFromConsoleException;

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
    public String[] getCommand() {
        try {
            String string = reader.readLine();
            return string.split("\\s");     // TODO name состоит из нескольких слов
        } catch (IOException e) {
            throw new ReadFromConsoleException(MESSAGE_EXCEPTION_EXPECTING_COMMAND);
        }
    }

    @Override
    public boolean actionsBeforeCommand(String[] args) {
        if (args.length == 0)
            return false;

        if (isExit(args[0])) {
            beforeExit();
            throw new ExitExpectedException();
        }

        if (isHelp(args[0])) {
            write(MESSAGE_HELP);
            return false;
        }
        return true;
    }

    @Override
    public void actionsAfterCommand(Operation operation, int id) {
        if (operation.equals(Operation.ADD))
            write(MESSAGE_ADD + id);
    }

    @Override
    public void write(String info) {
        System.out.println(info);
    }


    private void beforeExit() {
        try {
            reader.close();
        } catch (IOException ignored) {
        }
    }


    private boolean isExit(String operationString) {
        return COMMAND_QUIT.equals(operationString);
    }

    private boolean isHelp(String operationString) {
        return COMMAND_HELP.equals(operationString);
    }
}
