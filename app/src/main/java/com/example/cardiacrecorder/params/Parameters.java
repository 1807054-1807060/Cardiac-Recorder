package com.example.cardiacrecorder.params;

// • date measured (presented in dd-mm-yyyy format)
// • time measured (presented in hh:mm format)
// • systolic pressure in mm Hg (non-negative integer)
// • diastolic pressure in mm Hg (non-negative integer)
// • heart rate in beats per minute (non-negative integer)
// • comment (textual, up to 20 characters)

/**
 * This is a class to store the name of database name, table name and column name of our database
 */
public class Parameters {
    public static final int DB_VERSION= 1;
    public static final String DB_NAME= "cardiac_recorder_db";
    public static final String TABLE_NAME= "cardiac_records";

    public static final String KEY_ID= "id";
    public static final String KEY_NAME= "name";
    public static final String KEY_DATE= "date";
    public static final String KEY_TIME= "time";
    public static final String KEY_SYS_PRESSURE= "systolic_pressure";
    public static final String KEY_DIA_PRESSURE= "diastolic_pressure";
    public static final String KEY_HEART_RATE= "heart_rate";
    public static final String KEY_COMMENT= "comment";


}
