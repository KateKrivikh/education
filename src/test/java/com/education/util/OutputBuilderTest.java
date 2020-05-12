package com.education.util;

import com.education.entities.Sex;
import com.education.exceptions.domain.DateIsEmptyException;
import com.education.exceptions.domain.SexIsEmptyException;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class OutputBuilderTest {

    String output;

    int personId;
    String personName;
    Sex personSex;
    String personSexString;
    LocalDate personDate;
    String personDateString;

    public void fillPersonData() {
        personId = 1;
        personName = "name";
        personSex = Sex.MALE;
        personSexString = OutputBuilder.SEX_MALE;
        personDate = LocalDate.of(2003, 4, 13);
        personDateString = "13-Apr-2003";
    }

    @Test
    public void getPersonInfo_ExistingPerson_ExistingPersonInfo() {
        Given:
        fillPersonData();

        When:
        output = OutputBuilder.getPersonInfo(personId, personName, personSex, personDate);

        Then:
        Assert.assertEquals(String.format("%s %s %s", personName, personSexString, personDateString), output);
    }

    @Test
    public void getPersonInfo_RemovedPerson_RemovedPersonInfo() {
        Given:
        {
            fillPersonData();
            personName = null;
            personSex = null;
            personDate = null;
        }

        When:
        output = OutputBuilder.getPersonInfo(personId, personName, personSex, personDate);

        Then:
        Assert.assertEquals(String.format(OutputBuilder.MESSAGE_INFO_FOR_REMOVED_PERSON, personId), output);
    }

    @Test
    public void getPersonInfo_ExistingPersonNullName_ExistingPersonInfo() {
        Given:
        {
            fillPersonData();
            personName = null;
        }

        When:
        output = OutputBuilder.getPersonInfo(personId, personName, personSex, personDate);

        Then:
        Assert.assertEquals(String.format("%s %s %s", personName, personSexString, personDateString), output);
    }

    @Test
    public void getPersonInfo_ExistingPersonNullSex_ExistingPersonInfo() {
        Given:
        {
            fillPersonData();
            personSex = null;
            personSexString = "null";
        }

        When:
        output = OutputBuilder.getPersonInfo(personId, personName, personSex, personDate);

        Then:
        Assert.assertEquals(String.format("%s %s %s", personName, personSexString, personDateString), output);
    }

    @Test
    public void getPersonInfo_ExistingPersonNullDate_ExistingPersonInfo() {
        Given:
        {
            fillPersonData();
            personDate = null;
            personDateString = "null";
        }

        When:
        output = OutputBuilder.getPersonInfo(personId, personName, personSex, personDate);

        Then:
        Assert.assertEquals(String.format("%s %s %s", personName, personSexString, personDateString), output);
    }


    @Test
    public void writeSex_Male_FormattedString() {
        Sex sex;

        Given:
        sex = Sex.MALE;

        When:
        output = OutputBuilder.writeSex(sex);

        Then:
        Assert.assertEquals(OutputBuilder.SEX_MALE, output);
    }

    @Test
    public void writeSex_Female_FormattedString() {
        Sex sex;

        Given:
        sex = Sex.FEMALE;

        When:
        output = OutputBuilder.writeSex(sex);

        Then:
        Assert.assertEquals(OutputBuilder.SEX_FEMALE, output);
    }

    @Test(expected = SexIsEmptyException.class)
    public void writeSex_Null_Exception() {
        Sex sex;

        Given:
        sex = null;

        When:
        OutputBuilder.writeSex(sex);
    }


    @Test
    public void writeDate_Date_FormattedString() {
        LocalDate date;

        Given:
        date = LocalDate.of(2003, 4, 13);

        When:
        output = OutputBuilder.writeDate(date);

        Then:
        Assert.assertEquals("13-Apr-2003", output);
    }

    @Test(expected = DateIsEmptyException.class)
    public void writeDate_Null_Exception() {
        LocalDate date;

        Given:
        date = null;

        When:
        OutputBuilder.writeDate(date);
    }
}