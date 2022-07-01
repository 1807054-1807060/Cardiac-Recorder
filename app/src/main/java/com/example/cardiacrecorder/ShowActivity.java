package com.example.cardiacrecorder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    private TextView nameView;
    private TextView dateView;
    private TextView timeView;
    private TextView sysView;
    private TextView diaView;
    private TextView hrView;
    private TextView commentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameView = findViewById(R.id.stextView1);
        dateView = findViewById(R.id.stextView2);
        timeView = findViewById(R.id.stextView3);
        sysView = findViewById(R.id.stextView4);
        diaView = findViewById(R.id.stextView5);
        hrView = findViewById(R.id.stextView6);
        commentView = findViewById(R.id.stextView7);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            nameView.setText("Name: "+bundle.getString("name"));
            dateView.setText("Date: "+bundle.getString("date"));
            timeView.setText("TIme: " + bundle.getString("time"));
            sysView.setText("Systolic Pressure: " + bundle.getString("systolicPressure"));
            diaView.setText("Diastolic Pressure: " + bundle.getString("diastolicPressure"));
            hrView.setText("HeartRate: " + bundle.getString("heartRate"));
            commentView.setText("Comment: " + bundle.getString("comment"));


            if (Integer.parseInt(bundle.getString("systolicPressure")) < 90 || Integer.parseInt(bundle.getString("systolicPressure"))> 140)
            {
                sysView.setTextColor(Color.rgb(255,0,0));
            }
            if (Integer.parseInt(bundle.getString("diastolicPressure")) < 60 || Integer.parseInt(bundle.getString("diastolicPressure"))> 90)
            {
                diaView.setTextColor(Color.rgb(255,0,0));
            }
            if (Integer.parseInt(bundle.getString("heartRate")) < 60 || Integer.parseInt(bundle.getString("heartRate"))> 100)
            {
                hrView.setTextColor(Color.rgb(255,0,0));
            }
        }


    }
}