package com.example.cardiacrecorder.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cardiacrecorder.datetime.DateTime;
import com.example.cardiacrecorder.model.CardiacRecord;
import com.example.cardiacrecorder.params.Parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * This class to handle the local database(SQLite)
 */
public class DbHandler extends SQLiteOpenHelper {

    /**
     * Constructor of database handler
     * @param context
     */
    public DbHandler(Context context) {
        super(context, Parameters.DB_NAME, null, Parameters.DB_VERSION);
    }

    /**
     * While creatint the table on the database for the fist time
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE "+ Parameters.TABLE_NAME + " ( "
                +Parameters.KEY_ID + " INTEGER PRIMARY KEY, "
                +Parameters.KEY_NAME + " TEXT, "
                +Parameters.KEY_DATE + " TEXT, "
                +Parameters.KEY_TIME + " TEXT, "
                +Parameters.KEY_SYS_PRESSURE + " INTEGER, "
                +Parameters.KEY_DIA_PRESSURE + " INTEGER, "
                +Parameters.KEY_HEART_RATE + " INTEGER, "
                +Parameters.KEY_COMMENT + " TEXT" +")";
        db.execSQL(query);
    }

    /**
     * On Upgrade the database
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     *This function add new data in the local database
     * @param record record of the row to insert
     * @return the id of record that has been inserted
     */
    public long addRecord(CardiacRecord record){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Parameters.KEY_NAME, record.getName());
        values.put(Parameters.KEY_DATE, new DateTime().geDate());
        values.put(Parameters.KEY_TIME, new DateTime().getTime());
        values.put(Parameters.KEY_SYS_PRESSURE, record.getSystolicPressure());
        values.put(Parameters.KEY_DIA_PRESSURE,record.getDiastolicPressure());
        values.put(Parameters.KEY_HEART_RATE,record.getHeartRate());
        values.put(Parameters.KEY_COMMENT,record.getComment());
        long id = db.insert(Parameters.TABLE_NAME, null, values);
        Log.d("add-record", "addRecord: successfully added");
        db.close();
        return id;
    }

    /**
     * Check the data is exist or not
     * @param id the id of record, that has been inserted
     * @return true if data is exist, or false otherwise
     */
    public boolean checkIfDataExists(Long id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String Query = "Select * from " + Parameters.TABLE_NAME + " where " + Parameters.KEY_ID + " = " + Long.toString(id);
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    /**
     * To show all the records in database
     * @return list of records
     */
    public List<CardiacRecord> showRecords(){
        List<CardiacRecord> records = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "SELECT * FROM "+Parameters.TABLE_NAME;
        Cursor cursor= db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                CardiacRecord record= new CardiacRecord();
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setName(cursor.getString(1));
                record.setDate(cursor.getString(2));
                record.setTime(cursor.getString(3));
                record.setSystolicPressure(Integer.parseInt(cursor.getString(4)));
                record.setDiastolicPressure(Integer.parseInt(cursor.getString(5)));
                record.setHeartRate(Integer.parseInt(cursor.getString(6)));
                record.setComment(cursor.getString(7));
                records.add(record);
            }while(cursor.moveToNext());
        }
        db.close();
        return records;
    }

    /**
     * To update the record that already exist in the database
     * @param record that need to be modify/edit
     */
    public void updateRecord(CardiacRecord record){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(Parameters.KEY_NAME,record.getName());
        values.put(Parameters.KEY_DATE,record.getDate());
        values.put(Parameters.KEY_TIME,record.getTime());
        values.put(Parameters.KEY_SYS_PRESSURE,record.getSystolicPressure());
        values.put(Parameters.KEY_DIA_PRESSURE,record.getDiastolicPressure());
        values.put(Parameters.KEY_HEART_RATE,record.getHeartRate());
        values.put(Parameters.KEY_COMMENT,record.getComment());
        db.update(Parameters.TABLE_NAME,values,Parameters.KEY_ID+"=?", new String[]{String.valueOf(record.getId())});
        db.close();
    }

    /**
     * check update function
     * @param record
     * @param newName
     * @param newDate
     * @param newTime
     * @param newSystolicPressure
     * @param newDiastolicPressure
     * @param newHeartRate
     * @param newComment
     * @return true if data success updated on database or false otherwise
     */
    public boolean checkUpdated(CardiacRecord record, String newName, String newDate, String newTime, int newSystolicPressure, int newDiastolicPressure, int newHeartRate, String newComment)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query= "SELECT * FROM "+Parameters.TABLE_NAME + " where " + Parameters.KEY_ID + " = " +record.getId() ;
        Cursor cursor= db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                String dbName = cursor.getString(1);
                String dbDate = cursor.getString(1);
                String dbTime = cursor.getString(2);
                int dbSystolicPressure = Integer.parseInt(cursor.getString(4));
                int dbDiastolicPressure = Integer.parseInt(cursor.getString(5));
                int dbHeartRate = Integer.parseInt(cursor.getString(6));
                String dbComment = cursor.getString(7);

                if(dbName!=newName || dbDate!=newDate || dbTime!=newTime || dbSystolicPressure!=newSystolicPressure || dbDiastolicPressure!=newDiastolicPressure || dbHeartRate !=newHeartRate || dbComment!=newComment)
                {
                    cursor.close();
                    return true;
                }
            }while(cursor.moveToNext());
        }
        db.close();
        return false;








//        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
//        Cursor cursor = sqLiteDatabase.query(Parameters.TABLE_NAME, new String[]{Parameters.KEY_NAME}, Parameters.KEY_ID+" = '"+record.getId()+"'", null, null, null, null);
//        while (cursor.moveToNext()) {
//            int index1 = cursor.getColumnIndex(Parameters.KEY_NAME);
//            String dbName = cursor.getString(index1);
//            if(dbName != newName)
//            {
//                cursor.close();
//                return false;
//            }
//        }
//        return true;
    }

    /**
     * The the record the exist on the database
     * @param record that need to delete
     */
    public void deleteRecord(CardiacRecord record){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete(Parameters.TABLE_NAME,Parameters.KEY_ID+"=?", new String[]{String.valueOf(record.getId())});
        db.close();
    }
}
