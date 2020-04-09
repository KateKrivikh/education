package com.education;

import com.education.exceptions.InOutException;
import com.education.exceptions.PersonNotFoundException;
import com.education.exceptions.console.ExitExpectedException;
import com.education.inout.Controller;
import com.education.inout.ControllerConsole;

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
                String[] command = controller.getCommand();
                controller.executeCommand(command);
            } catch (ExitExpectedException e) {
                break;
            } catch (InOutException | PersonNotFoundException e) {
                controller.write(e.getMessage());
            }
        }
    }

}