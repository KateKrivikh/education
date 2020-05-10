package com.education.util;

import com.education.controller.command.Operation;
import com.education.entities.Sex;
import com.education.exceptions.inout.incorrectInput.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InputParserTest {

    @Test
    public void parseOperationAdd() {
        Operation actual = InputParser.parseOperation(InputParser.PARAM_OPERATION_ADD);
        Operation expected = Operation.ADD;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseOperationUpdate() {
        Operation actual = InputParser.parseOperation(InputParser.PARAM_OPERATION_UPDATE);
        Operation expected = Operation.UPDATE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseOperationRemove() {
        Operation actual = InputParser.parseOperation(InputParser.PARAM_OPERATION_REMOVE);
        Operation expected = Operation.REMOVE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseOperationInfo() {
        Operation actual = InputParser.parseOperation(InputParser.PARAM_OPERATION_INFO);
        Operation expected = Operation.INFO;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IncorrectOperationException.class)
    public void parseOperationElse() {
        String someString = "test";
        InputParser.parseOperation(someString);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseOperationNull() {
        String nullString = null;
        InputParser.parseOperation(nullString);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseOperationEmpty() {
        String emptyString = "   ";
        InputParser.parseOperation(emptyString);
    }


    @Test
    public void checkParametersCount() {
        String[] parameters = {"-i", "1"};
        InputParser.checkParametersCount(2, parameters);
        Boolean actual = true;
        Boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IncorrectOperationParametersCountException.class)
    public void checkParametersCountWrong() {
        int parametersCount = 1;
        InputParser.checkParametersCount(parametersCount);
    }


    @Test
    public void parseIdNumber() {
        int actual = InputParser.parseId("1");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseIdNumber0() {
        String is0Id = "0";
        InputParser.parseId(is0Id);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseIdNumberNegative() {
        String negativeId = "-1";
        InputParser.parseId(negativeId);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseIdNotInt() {
        String notIntId = "1.5";
        InputParser.parseId(notIntId);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseIdNotNumber() {
        String notNumberId = "a";
        InputParser.parseId(notNumberId);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseIdNull() {
        String nullId = null;
        InputParser.parseId(nullId);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseIdEmpty() {
        String emptyId = "   ";
        InputParser.parseId(emptyId);
    }


    @Test
    public void parseSexMale() {
        Sex actual = InputParser.parseSex(InputParser.PARAM_SEX_MALE);
        Sex expected = Sex.MALE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseSexFemale() {
        Sex actual = InputParser.parseSex(InputParser.PARAM_SEX_FEMALE);
        Sex expected = Sex.FEMALE;
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IncorrectSexException.class)
    public void parseSexElse() {
        String someSex = "м.";
        InputParser.parseSex(someSex);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseSexNull() {
        String nullSex = null;
        InputParser.parseSex(nullSex);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseSexEmpty() {
        String emptySex = "   ";
        InputParser.parseSex(emptySex);
    }


    @Test
    public void parseDate() {
        LocalDate actual = InputParser.parseDate("12/04/2000");
        LocalDate expected = LocalDate.of(2000, 4, 12);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IncorrectDateException.class)
    public void parseDateWrongFormat() {
        String date = "12-04-2000";
        InputParser.parseDate(date);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseDateNull() {
        String nullDate = null;
        InputParser.parseDate(nullDate);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseDateEmpty() {
        String emptyDate = "   ";
        InputParser.parseDate(emptyDate);
    }
}