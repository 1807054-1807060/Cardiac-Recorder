package com.example.cardiacrecorder.handler;

import com.example.cardiacrecorder.datetime.DateTime;
import com.example.cardiacrecorder.model.CardiacRecord;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;


import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;


@RunWith(RobolectricTestRunner.class)
public class DbHandlerTest extends TestCase {

    @Test
    public void test()
    {
        assertEquals(2, 1+1);
        assertEquals(3, 1+2);
    }

    @Test
    public void testAdd()
    {
//        assertEquals(3, 1+2);
        String name = "name1";
        String date = new DateTime().geDate();
        String time = new DateTime().getTime();
        int systolicPressure = 50;
        int diastolicPressure =100;
        int heartRate = 100;
        String comment = "Comment";

        CardiacRecord record = new CardiacRecord(name, date, time, systolicPressure, diastolicPressure, heartRate, comment);


//        Database insert
        DbHandler db = new DbHandler(RuntimeEnvironment.getApplication());
        long id = db.addRecord(record);

//        check either this id is exist or not
        assertTrue(db.checkIfDataExists(id));

        db.close();
    }

    @Test
    public void testDelete() {
        String name = "name1";
        String date = new DateTime().geDate();
        String time = new DateTime().getTime();
        int systolicPressure = 50;
        int diastolicPressure =100;
        int heartRate = 100;
        String comment = "Comment";

        CardiacRecord record = new CardiacRecord(name, date, time, systolicPressure, diastolicPressure, heartRate, comment);

        DbHandler db = new DbHandler(RuntimeEnvironment.getApplication());

//        Database insert
        long id = db.addRecord(record);
        record.setId((int) id);


        //delete record
        db.deleteRecord(record);

        assertFalse(db.checkIfDataExists(id));
    }

    @Test
    public void testUpdate() {
        String name = "name1";
        String date = new DateTime().geDate();
        String time = new DateTime().getTime();
        int systolicPressure = 50;
        int diastolicPressure =100;
        int heartRate = 100;
        String comment = "Comment";

        CardiacRecord record = new CardiacRecord(name, date, time, systolicPressure, diastolicPressure, heartRate, comment);

        DbHandler db = new DbHandler(RuntimeEnvironment.getApplication());

//        Database insert
        long id = db.addRecord(record);
        record.setId((int) id);


        //now updating
        String newName= "name2";
        String newDate = new DateTime().geDate();
        String newTime = new DateTime().getTime();
        int newSystolicPressure = 70;
        int newDiastolicPressure = 80;
        int newHeartRate = 78;
        String newComment = "NewComment";

        record.setName(newName);
        record.setDate(newDate);
        record.setTime(newTime);
        record.setSystolicPressure(newSystolicPressure);
        record.setDiastolicPressure(newDiastolicPressure);
        record.setHeartRate(newHeartRate);
        record.setComment(newComment);

        db.updateRecord(record);

        assertTrue(db.checkUpdated(record, newName, newDate, newTime, newSystolicPressure, newDiastolicPressure, newHeartRate, newComment));
    }


}