package main.java.com.education.controller;

import main.java.com.education.controller.command.Command;
import main.java.com.education.exceptions.inout.console.QuitExpectedException;
import main.java.com.education.exceptions.inout.console.ReadFromConsoleException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectOperationException;
import main.java.com.education.util.InputParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ControllerConsole extends Controller implements AutoCloseable {
    public static final String COMMAND_QUIT = "-q";
    public static final String COMMAND_HELP = "-h";
    public static final String MESSAGE_HELP = InputParser.MESSAGE_OPERATIONS + "\n" +
            "-q                             - выход\n" +
            "-h                             - помощь";
    public static final String MESSAGE_EXCEPTION_EXPECTING_COMMAND = "Ошибка при чтении команды из консоли";

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ControllerConsole() {
        write(MESSAGE_HELP);
    }

    @Override
    public String read() throws ReadFromConsoleException {
        try {
            return reader.readLine();
        } catch (IOException e) {
            throw new ReadFromConsoleException(MESSAGE_EXCEPTION_EXPECTING_COMMAND);
        }
    }

    @Override
    public void write(String info) {
        System.out.println(info);
    }

    @Override
    public Command parse(String commandString) throws IncorrectInputException, QuitExpectedException {
        try {
            return super.parse(commandString);
        } catch (IncorrectOperationException e) {
            if (isQuit(commandString)) {
                return this.new ConsoleCommandQuit();
            } else if (isHelp(commandString)) {
                return this.new ConsoleCommandHelp();
            } else
                throw e;
        }
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }


    private boolean isHelp(String operationString) {
        return COMMAND_HELP.equals(operationString);
    }

    private boolean isQuit(String commandString) {
        return COMMAND_QUIT.equals(commandString);
    }

    public abstract class ConsoleCommand extends Command {
    }

    public class ConsoleCommandQuit extends ConsoleCommand {
        @Override
        public void execute() throws QuitExpectedException {
            try {
                close();
            } catch (IOException ignored) {
            }
            throw new QuitExpectedException();
        }
    }

    public class ConsoleCommandHelp extends ConsoleCommand {
        @Override
        public void execute() {
            write(MESSAGE_HELP);
        }
    }
}
