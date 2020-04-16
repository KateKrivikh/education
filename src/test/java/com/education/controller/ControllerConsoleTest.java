package test.java.com.education.controller;

import main.java.com.education.controller.ControllerConsole;
import main.java.com.education.controller.command.Command;
import main.java.com.education.controller.command.CrudCommand;
import main.java.com.education.controller.command.PersonCommandInfo;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.exceptions.domain.PersonNotFoundException;
import main.java.com.education.exceptions.inout.console.QuitExpectedException;
import main.java.com.education.exceptions.inout.incorrectInput.EmptyCommandException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectInputException;
import main.java.com.education.exceptions.inout.incorrectInput.IncorrectOperationException;
import org.junit.Assert;
import org.junit.Test;
import test.java.com.education.UtilTest;

import java.io.*;

public class ControllerConsoleTest {

    @Test
    public void read() {
        String someString = "some text";

        InputStream console = System.in;

        InputStream inputStream = new ByteArrayInputStream(someString.getBytes());
        System.setIn(inputStream);

        ControllerConsole controller = new ControllerConsole();
        String actual = controller.read();
        String expected = someString;

        System.setIn(console);
        try {
            inputStream.close();
        } catch (IOException ignored) {
        }

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void write() {
        String someString = "some text";
        ControllerConsole controller = new ControllerConsole();

        PrintStream console = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        controller.write(someString);

        String expected = someString + "\r\n";
        String actual = outputStream.toString();

        System.setOut(console);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void parseCommandQuit() {
        ControllerConsole controller = new ControllerConsole();
        Command actual = controller.parse("-q");
        Command expected = controller.new ConsoleCommandQuit();
        Assert.assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    public void parseCommandHelp() {
        ControllerConsole controller = new ControllerConsole();
        Command actual = controller.parse("-h");
        Command expected = controller.new ConsoleCommandHelp();
        Assert.assertEquals(expected.getClass(), actual.getClass());
    }

    @Test
    public void parseCommandCrud() {
        ControllerConsole controller = new ControllerConsole();
        CrudCommand actual = (CrudCommand) controller.parse("-i 2");
        CrudCommand expected = new PersonCommandInfo();
        expected.setParameters("2");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseCommandUnknown() {
        ControllerConsole controller = new ControllerConsole();
        String commandUnknown = "-e";
        try {
            controller.parse(commandUnknown);
            Assert.fail("IncorrectInputException expected");
        } catch (IncorrectInputException actual) {
            IncorrectOperationException expected = new IncorrectOperationException(commandUnknown);
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void parseCommandEmpty() {
        ControllerConsole controller = new ControllerConsole();
        String commandEmpty = "";
        try {
            controller.parse(commandEmpty);
            Assert.fail("EmptyCommandException expected");
        } catch (EmptyCommandException actual) {
            EmptyCommandException expected = new EmptyCommandException();
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void parseCommandNull() {
        ControllerConsole controller = new ControllerConsole();
        String commandNull = null;
        try {
            controller.parse(commandNull);
            Assert.fail("EmptyCommandException expected");
        } catch (EmptyCommandException actual) {
            EmptyCommandException expected = new EmptyCommandException();
            Assert.assertEquals(expected, actual);
        }
    }


    @Test
    public void executeQuit() {
        ControllerConsole controller = new ControllerConsole();

        Command command = controller.new ConsoleCommandQuit();

        try {
            controller.execute(command);
            Assert.fail("QuitExpectedException expected");
        } catch (QuitExpectedException actual) {
            QuitExpectedException expected = new QuitExpectedException();
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void executeHelp() {
        ControllerConsole controller = new ControllerConsole();

        Command command = controller.new ConsoleCommandHelp();

        PrintStream console = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        controller.execute(command);

        String actual = outputStream.toString();
        String expected = ControllerConsole.MESSAGE_HELP + "\r\n";

        System.setOut(console);
        stream.close();
        try {
            outputStream.close();
        } catch (IOException ignored) {
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void executeCrud() {
        UtilTest.fillPerson();
        int minId = UtilTest.getMinPersonId();

        ControllerConsole controller = new ControllerConsole();

        CrudCommand command = new PersonCommandInfo();
        command.setParameters(String.valueOf(minId));


        PrintStream console = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

        try {
            controller.execute(command);
            String expected = command.getResult() + "\r\n";

            String actual = outputStream.toString();

            System.setOut(console);
            Assert.assertEquals(expected, actual);
        } catch (DomainExceptions e) {
            System.setOut(console);
            Assert.fail("Unexpected exception");
            e.printStackTrace();
        }
    }

    @Test
    public void executeCrudPersonNotFound() {
        UtilTest.fillPerson();
        int minId = UtilTest.getMinPersonId();
        int idWrong = minId + 4;

        CrudCommand command = new PersonCommandInfo();
        command.setParameters(String.valueOf(idWrong));

        ControllerConsole controller = new ControllerConsole();
        try {
            controller.execute(command);
            Assert.fail("PersonNotFoundException expected");
        } catch (PersonNotFoundException actual) {
            PersonNotFoundException expected = new PersonNotFoundException(idWrong);
            Assert.assertEquals(expected, actual);
        }
    }

}