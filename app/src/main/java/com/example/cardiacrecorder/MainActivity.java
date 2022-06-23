package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.cardiacrecorder.adapter.RecyclerViewAdapter;
import com.example.cardiacrecorder.handler.DbHandler;
import com.example.cardiacrecorder.model.CardiacRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<CardiacRecord> allRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DbHandler dbHandler = new DbHandler(MainActivity.this);
        allRecords= dbHandler.showRecords();
        for (CardiacRecord record: allRecords){
            Log.d("show", ""+ record.getName());
        }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, allRecords);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}