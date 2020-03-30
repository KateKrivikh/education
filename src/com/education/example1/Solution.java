package com.education.example1;

import com.education.example1.commands.Command;
import com.education.example1.commands.CommandFactory;
import com.education.example1.commands.Operation;
import com.education.example1.exceptions.IncorrectInputException;
import com.education.example1.exceptions.PersonNotFoundException;

import java.util.Arrays;

/**
 * Provides CRUD operations.
 * Params for operations set as program arguments.
 * There are 4 available operations:
 * -c - adding new person:
 *      -c Сидоров м 24/03/1999
 * -u - updating existing person:
 *      -u 2 Сидорова ж 25/02/2001
 * -d - removing existing person:
 *      -d 2
 * -i - print information about existing person in console:
 *      -i 2
 *
 * At the moment operation can be only one.
 * I think, there should be package operations. Maybe list of different kind of operations.
 * Maybe, operations with parameters should entered by person in console.
 * Maybe, need to add operation-info by all the person list.
 */
public class Solution {

    // TODO возможно, надо задавать динамические parser и writer. Подумать


    public static void main(String[] args) {
        // TODO с переходом на ввод из консоли убрать
        if (args.length == 0) {
            System.out.println("Запускать код нужно с параметрами!");
            System.exit(-1);
        }

        try {
            Operation operation = InputParser.parseOperation(args[0]);
            Command command = CommandFactory.create(operation);
            int id = command.execute(Arrays.copyOfRange(args, 1, args.length));

            // TODO пока так для проверки
            System.out.println("-------------------");
            Command info = CommandFactory.create(Operation.INFO);
            for (int i = 0; i < PersonRepository.allPeople.size(); i++) {
                String[] argsInfo = new String[]{String.valueOf(i)};
                info.execute(argsInfo);
            }

        } catch (IncorrectInputException | PersonNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}