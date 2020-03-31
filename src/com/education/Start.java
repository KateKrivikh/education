package com.education;

import com.education.exceptions.ExitExpectedException;
import com.education.exceptions.ExpectingCommandException;
import com.education.exceptions.IncorrectInputException;
import com.education.exceptions.PersonNotFoundException;
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
 * At the moment operation can be only one.
 * I think, there should be package operations. Maybe list of different kind of operations.
 * Maybe, need to add operation-info by all the person list.
 */
public class Start {

    // пока что работаем с консолью
    public static Controller controller = new ControllerConsole();

    public static void main(String[] args) {
        while (true) {
            try {
                String[] command = controller.getCommand();
                controller.executeCommand(command);
            } catch (ExpectingCommandException | IncorrectInputException | PersonNotFoundException e) {
                controller.write(e.getMessage());
            } catch (ExitExpectedException e) {
                break;
            }
        }
    }

}