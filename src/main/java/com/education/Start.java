package com.education;

import com.education.controller.command.Command;
import com.education.exceptions.inout.InOutException;
import com.education.exceptions.domain.DomainException;
import com.education.controller.Controller;
import com.education.controller.ControllerConsole;
import com.education.exceptions.inout.console.QuitExpectedException;

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
            } catch (QuitExpectedException e) {
                break;
            } catch (InOutException | DomainException e) {
                controller.write(e.getMessage());
                if (e.getCause() != null)
                    controller.write(e.getCause().getMessage());
            }
        }
    }

}