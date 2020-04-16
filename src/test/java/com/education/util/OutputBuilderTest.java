package test.java.com.education.util;

import main.java.com.education.entities.Sex;
import main.java.com.education.exceptions.domain.DateIsEmptyException;
import main.java.com.education.exceptions.domain.SexIsEmptyException;
import main.java.com.education.util.OutputBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class OutputBuilderTest {

    @Test
    public void getPersonInfo() {
        int id = 1;
        String name = "name";
        Sex sex = Sex.MALE;
        String sexString = OutputBuilder.SEX_MALE;
        LocalDate date = LocalDate.of(2003, 4, 13);
        String dateString = "13-Apr-2003";
        String delimiter = " ";

        String actual = OutputBuilder.getPersonInfo(id, name, sex, date);
        String expected = name + delimiter + sexString + delimiter + dateString;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPersonInfoRemoved() {
        int id = 1;
        String name = null;
        Sex sex = null;
        LocalDate date = null;

        String actual = OutputBuilder.getPersonInfo(id, name, sex, date);
        String expected = String.format(OutputBuilder.MESSAGE_INFO_FOR_REMOVED_PERSON, id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPersonNullName() {
        int id = 1;
        String name = null;
        Sex sex = Sex.MALE;
        String sexString = OutputBuilder.SEX_MALE;
        LocalDate date = LocalDate.of(2003, 4, 13);
        String dateString = "13-Apr-2003";
        String delimiter = " ";

        String actual = OutputBuilder.getPersonInfo(id, name, sex, date);
        String expected = name + delimiter + sexString + delimiter + dateString;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPersonNullSex() {
        int id = 1;
        String name = "name";
        Sex sex = null;
        String sexString = "null";
        LocalDate date = LocalDate.of(2003, 4, 13);
        String dateString = "13-Apr-2003";
        String delimiter = " ";

        String actual = OutputBuilder.getPersonInfo(id, name, sex, date);
        String expected = name + delimiter + sexString + delimiter + dateString;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPersonNullDate() {
        int id = 1;
        String name = "name";
        Sex sex = Sex.MALE;
        String sexString = OutputBuilder.SEX_MALE;
        LocalDate date = null;
        String dateString = "null";
        String delimiter = " ";

        String actual = OutputBuilder.getPersonInfo(id, name, sex, date);
        String expected = name + delimiter + sexString + delimiter + dateString;
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void writeSexMale() {
        String actual = OutputBuilder.writeSex(Sex.MALE);
        String expected = OutputBuilder.SEX_MALE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeSexFemale() {
        String actual = OutputBuilder.writeSex(Sex.FEMALE);
        String expected = OutputBuilder.SEX_FEMALE;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeSexNull() {
        try {
            OutputBuilder.writeSex(null);
            Assert.fail("Должно выпасть исключение, что пол не указан");
        } catch (SexIsEmptyException actual) {
            SexIsEmptyException expected = new SexIsEmptyException();
            Assert.assertEquals(expected, actual);
        }
    }


    @Test
    public void writeDate() {
        String actual = OutputBuilder.writeDate(LocalDate.of(2003, 4, 13));
        String expected = "13-Apr-2003";
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void writeDateNull() {
        try {
            OutputBuilder.writeDate(null);
        } catch (DateIsEmptyException actual) {
            DateIsEmptyException expected = new DateIsEmptyException();
            Assert.assertEquals(expected, actual);
        }
    }
}