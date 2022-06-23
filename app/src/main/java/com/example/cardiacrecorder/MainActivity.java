package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.cardiacrecorder.handler.DbHandler;
import com.example.cardiacrecorder.model.CardiacRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHandler dbHandler = new DbHandler(MainActivity.this);
        List<CardiacRecord> allRecords= dbHandler.showRecords();
        for (CardiacRecord record: allRecords){
            Log.d("show_records", ""+record.getName()+" "+ record.getDate()+" "+record.getTime());
        }

    }
}