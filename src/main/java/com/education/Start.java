package main.java.com.education;

import main.java.com.education.controller.command.Command;
import main.java.com.education.exceptions.inout.InOutException;
import main.java.com.education.exceptions.domain.DomainExceptions;
import main.java.com.education.controller.Controller;
import main.java.com.education.controller.ControllerConsole;
import main.java.com.education.exceptions.inout.console.ExitExpectedException;

/**
 * Provides CRUD operations.
 * There are 4 available operations:
 * -c - adding new person:
 *      -c Сидоров м 24/03/1999
 * -u - updating existing person:
 *      -u 2 Сидорова ж 25/02/2001
 * -d - removing existing person:
 *      -d 2
 * -i - print information about existing person in console:
 *      -i 2
 * <p/>
 * At the moment operations can be entered from console while operation "quit" is entered.
 * Maybe, need to add operation-info by all the person list.
 */
public class Start {

    public static Controller controller = new ControllerConsole();

    public static void main(String[] args) {
        while (true) {
            try {
                String commandString = controller.read();
                Command command = controller.parse(commandString);
                controller.execute(command);
            } catch (ExitExpectedException e) {
                break;
            } catch (InOutException | DomainExceptions e) {
                controller.write(e.getMessage());
                if (e.getCause() != null)
                    controller.write(e.getCause().getMessage());
            }
        }
    }

}