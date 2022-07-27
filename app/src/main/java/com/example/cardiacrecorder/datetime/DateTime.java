package com.example.cardiacrecorder.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This is a class to get the current date and time from the system
 */
public class DateTime {
    /**
     * Return the time in HH:mm format
     * @return
     */
    public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    /**
     * Return the date in dd/MM/yyyy format
     * @return
     */
    public String geDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

}
