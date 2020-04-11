package test.java.com.education.util;

import main.java.com.education.controller.commands.Operation;
import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.inout.incorrectInput.*;
import main.java.com.education.util.InputParser;
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

    @Test
    public void parseOperationElse() {
        String someString = "test";
        IncorrectOperationException expected = new IncorrectOperationException(someString);
        try {
            InputParser.parseOperation(someString);
        } catch (IncorrectOperationException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }

        IncorrectOperationException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseOperationNull() {
        String nullString = null;
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_OPERATION);
        try {
            InputParser.parseOperation(nullString);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseOperationEmpty() {
        String emptyString = "   ";
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_OPERATION);
        try {
            InputParser.parseOperation(emptyString);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void checkParametersCount() {
        String[] parameters = {"-i", "1"};
        InputParser.checkParametersCount(2, parameters);
        Boolean actual = true;
        Boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void checkParametersCountWrong() {
        int parametersCount = 1;
        IncorrectOperationParametersCountException expected = new IncorrectOperationParametersCountException(parametersCount);
        try {
            InputParser.checkParametersCount(parametersCount);
        } catch (IncorrectOperationParametersCountException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectOperationParametersCountException actual = null;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void parseIdNumber() {
        int actual = InputParser.parseId("1");
        int expected = 1;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseIdNumber0() {
        String is0Id = "0";
        IncorrectIdException expected = new IncorrectIdException(is0Id);
        try {
            InputParser.parseId(is0Id);
        } catch (IncorrectIdException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectIdException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseIdNumberNegative() {
        String negativeId = "-1";
        IncorrectIdException expected = new IncorrectIdException(negativeId);
        try {
            InputParser.parseId(negativeId);
        } catch (IncorrectIdException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectIdException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseIdNotInt() {
        String notIntId = "1.5";
        IncorrectIdException expected = new IncorrectIdException(notIntId);
        try {
            InputParser.parseId(notIntId);
        } catch (IncorrectIdException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectIdException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseIdNotNumber() {
        String notNumberId = "a";
        IncorrectIdException expected = new IncorrectIdException(notNumberId);
        try {
            InputParser.parseId(notNumberId);
        } catch (IncorrectIdException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectIdException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseIdNull() {
        String nullId = null;
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_ID);
        try {
            InputParser.parseId(nullId);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseIdEmpty() {
        String emptyId = "   ";
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_ID);
        try {
            InputParser.parseId(emptyId);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
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

    @Test
    public void parseSexElse() {
        String someSex = "м.";
        IncorrectSexException expected = new IncorrectSexException(someSex, InputParser.SEX_FORMATS_FOR_MESSAGE);
        try {
            InputParser.parseSex(someSex);
        } catch (IncorrectSexException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectSexException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseSexNull() {
        String nullSex = null;
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_SEX);
        try {
            InputParser.parseSex(nullSex);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseSexEmpty() {
        String emptySex = "   ";
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_SEX);
        try {
            InputParser.parseSex(emptySex);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void parseDate() {
        LocalDate actual = InputParser.parseDate("12/04/2000");
        LocalDate expected = LocalDate.of(2000, 4, 12);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseDateWrongFormat() {
        String date = "12-04-2000";
        IncorrectDateException expected = new IncorrectDateException(date, InputParser.DATE_FORMAT_FOR_INPUT);
        try {
            InputParser.parseDate(date);
        } catch (IncorrectDateException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        IncorrectDateException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseDateNull() {
        String nullDate = null;
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_DATE);
        try {
            InputParser.parseDate(nullDate);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseDateEmpty() {
        String emptyDate = "   ";
        EmptyParameterException expected = new EmptyParameterException(InputParser.PARAM_NAME_DATE);
        try {
            InputParser.parseDate(emptyDate);
        } catch (EmptyParameterException actual) {
            Assert.assertEquals(expected, actual);
            return;
        }
        EmptyParameterException actual = null;
        Assert.assertEquals(expected, actual);
    }
}