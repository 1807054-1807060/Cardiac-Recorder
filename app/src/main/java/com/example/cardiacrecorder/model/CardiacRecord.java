package com.example.cardiacrecorder.model;

// • date measured (presented in dd-mm-yyyy format)
// • time measured (presented in hh:mm format)
// • systolic pressure in mm Hg (non-negative integer)
// • diastolic pressure in mm Hg (non-negative integer)
// • heart rate in beats per minute (non-negative integer)
// • comment (textual, up to 20 characters)

public class CardiacRecord {
    private int id;
    private String name;
    private String date;
    private String time;
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private String comment;

    public CardiacRecord(String name, String date, String time, int systolicPressure, int diastolicPressure, int heartRate, String comment) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public CardiacRecord(String name, int systolicPressure, int diastolicPressure, int heartRate, String comment) {
        this.name = name;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.comment = comment;
    }

    public CardiacRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(int systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public int getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(int diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
