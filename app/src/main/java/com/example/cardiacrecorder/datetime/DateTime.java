package com.example.cardiacrecorder.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;
public class DateTime {
    public String getTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return formatter.format(date);
    }

    public String geDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

}
