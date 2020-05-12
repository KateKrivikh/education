package com.education.util;

import com.education.controller.command.Operation;
import com.education.entities.Sex;
import com.education.exceptions.inout.incorrectInput.*;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InputParserTest {

    String input;
    Object output;

    @Test
    public void parseOperation_C_Add() {
        Given:
        input = "-c";

        When:
        output = InputParser.parseOperation(input);

        Then:
        Assert.assertEquals(Operation.ADD, output);
    }

    @Test
    public void parseOperation_U_Update() {
        Given:
        input = "-u";

        When:
        output = InputParser.parseOperation(input);

        Then:
        Assert.assertEquals(Operation.UPDATE, output);
    }

    @Test
    public void parseOperation_D_Remove() {
        Given:
        input = "-d";

        When:
        output = InputParser.parseOperation(input);

        Then:
        Assert.assertEquals(Operation.REMOVE, output);
    }

    @Test
    public void parseOperation_I_Info() {
        Given:
        input = "-i";

        When:
        output = InputParser.parseOperation(input);

        Then:
        Assert.assertEquals(Operation.INFO, output);
    }

    @Test(expected = IncorrectOperationException.class)
    public void parseOperation_Else_Exception() {
        Given:
        input = "else";

        When:
        output = InputParser.parseOperation(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseOperation_Null_Exception() {
        Given:
        input = null;

        When:
        output = InputParser.parseOperation(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseOperation_Empty_Exception() {
        Given:
        input = "    ";

        When:
        output = InputParser.parseOperation(input);
    }


    @Test
    public void checkParametersCount_I2_True() {
        int parametersCount;
        String[] parameters;

        Given:
        {
            parametersCount = 2;
            parameters = new String[]{"-i", "1"};
        }

        When:
        InputParser.checkParametersCount(parametersCount, parameters);
    }

    @Test(expected = IncorrectOperationParametersCountException.class)
    public void checkParametersCount_I1_Exception() {
        int parametersCount;
        String[] parameters;

        Given:
        {
            parametersCount = 2;
            parameters = new String[]{"-i"};
        }

        When:
        InputParser.checkParametersCount(parametersCount, parameters);
    }


    @Test
    public void parseId_1_1() {
        Given:
        input = "1";

        When:
        output = InputParser.parseId(input);

        Then:
        Assert.assertEquals(1, output);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseId_0_Exception() {
        Given:
        input = "0";

        When:
        output = InputParser.parseId(input);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseId_Negative_Exception() {
        Given:
        input = "-1";

        When:
        output = InputParser.parseId(input);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseId_NotInt_Exception() {
        Given:
        input = "1.5";

        When:
        output = InputParser.parseId(input);
    }

    @Test(expected = IncorrectIdException.class)
    public void parseId_NotNumber_Exception() {
        Given:
        input = "some text";

        When:
        output = InputParser.parseId(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseId_Null_Exception() {
        Given:
        input = null;

        When:
        output = InputParser.parseId(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseId_Empty_Exception() {
        Given:
        input = "    ";

        When:
        output = InputParser.parseId(input);
    }


    @Test
    public void parseSex_Male_Male() {
        Given:
        input = InputParser.PARAM_SEX_MALE;

        When:
        output = InputParser.parseSex(input);

        Then:
        Assert.assertEquals(Sex.MALE, output);
    }

    @Test
    public void parseSex_Female_Female() {
        Given:
        input = InputParser.PARAM_SEX_FEMALE;

        When:
        output = InputParser.parseSex(input);

        Then:
        Assert.assertEquals(Sex.FEMALE, output);
    }

    @Test(expected = IncorrectSexException.class)
    public void parseSex_Else_Exception() {
        Given:
        input = "man";

        When:
        output = InputParser.parseSex(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseSex_Null_Exception() {
        Given:
        input = null;

        When:
        output = InputParser.parseSex(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseSex_Empty_Exception() {
        Given:
        input = "    ";

        When:
        output = InputParser.parseSex(input);
    }


    @Test
    public void parseDate_AvailableFormat_Date() {
        Given:
        input = "12/04/2000";

        When:
        output = InputParser.parseDate(input);

        Then:
        Assert.assertEquals(LocalDate.of(2000, 4, 12), output);
    }

    @Test(expected = IncorrectDateException.class)
    public void parseDate_WrongFormat_Exception() {
        Given:
        input = "12-04-2000";

        When:
        output = InputParser.parseDate(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseDate_Null_Exception() {
        Given:
        input = null;

        When:
        output = InputParser.parseDate(input);
    }

    @Test(expected = EmptyParameterException.class)
    public void parseDate_Empty_Exception() {
        Given:
        input = "    ";

        When:
        output = InputParser.parseDate(input);
    }
}