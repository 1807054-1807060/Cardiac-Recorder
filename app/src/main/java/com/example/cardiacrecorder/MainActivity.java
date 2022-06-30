package com.example.cardiacrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.cardiacrecorder.adapter.RecyclerViewAdapter;
import com.example.cardiacrecorder.handler.DbHandler;
import com.example.cardiacrecorder.model.CardiacRecord;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<CardiacRecord> allRecords;
    private FloatingActionButton button;
     AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= findViewById(R.id.add_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        recyclerView= findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DbHandler dbHandler = new DbHandler(MainActivity.this);
        allRecords= dbHandler.showRecords();
        for (CardiacRecord record: allRecords){
            Log.d("show", ""+ record.getName());
        }
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this);
        recyclerViewAdapter.setRecordList(allRecords);
        recyclerView.setAdapter(recyclerViewAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBackMethod);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }


    ItemTouchHelper.SimpleCallback callBackMethod = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAbsoluteAdapterPosition();

            switch (direction) {
                case ItemTouchHelper.LEFT:
                    //delete;
                    DbHandler handler = new DbHandler(MainActivity.this);
                    handler.deleteRecord(allRecords.get(position));
                    allRecords = handler.showRecords();
                    recyclerViewAdapter.setRecordList(allRecords);
                    recyclerView.getAdapter().notifyItemRemoved(position);
                    break;
                case ItemTouchHelper.RIGHT:
                    openDialog(allRecords.get(position));

                    recyclerView.getAdapter().notifyItemChanged(position);
                    break;
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(getResources().getColor(R.color.white))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .setSwipeLeftActionIconTint(getResources().getColor(R.color.white))
                    .addSwipeLeftBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_default_color_error))
                    .addSwipeRightLabel("Edit")
                    .setSwipeRightLabelColor(getResources().getColor(R.color.white))
                    .addSwipeRightActionIcon(R.drawable.ic_baseline_edit_24)
                    .setSwipeRightActionIconTint(getResources().getColor(R.color.white))
                    .addSwipeRightBackgroundColor(getResources().getColor(com.google.android.material.R.color.design_dark_default_color_secondary_variant))
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        }
    };

    public void openDialog(){
        EditText editTextName;
        EditText editTextSys;
        EditText editTextDia;
        EditText editTextHr;
        EditText editTextComment;
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getLayoutInflater().inflate(R.layout.dialog_box, null);
        editTextName = view.findViewById(R.id.name_field);
        editTextSys = view.findViewById(R.id.systolic_field);
        editTextDia = view.findViewById(R.id.diastolic_field);
        editTextHr = view.findViewById(R.id.heart_rate_field);
        editTextComment = view.findViewById(R.id.comments_field);
        builder.setView(view);
        dialog= builder.create();
        dialog.show();
        dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String sysBpStr = editTextSys.getText().toString();
                String diaBpStr = editTextDia.getText().toString();
                String hRateStr = editTextHr.getText().toString();
                String comment = editTextComment.getText().toString();

                    if(TextUtils.isEmpty(name) || TextUtils.isEmpty(sysBpStr) || TextUtils.isEmpty(diaBpStr) || TextUtils.isEmpty(hRateStr)){
                        if(TextUtils.isEmpty(name)){
                            editTextName.setError("Empty Field");
                            editTextName.setHint("Name Required");
                        }
                        if(TextUtils.isEmpty(sysBpStr)){
                            editTextSys.setError("Empty Field");
                            editTextSys.setHint("Systolic Pressure Required");
                        }
                        if ( TextUtils.isEmpty(diaBpStr)){
                            editTextDia.setError("Empty Field");
                            editTextDia.setHint("Diastolic Pressure Required");
                        }
                        if ( TextUtils.isEmpty(hRateStr)){
                            editTextHr.setError("empty");
                            editTextHr.setHint("Heart Rate Required");
                        }
                    }


                else{
                        int sysBp = Integer.parseInt(sysBpStr);
                        int diaBp = Integer.parseInt(diaBpStr);
                        int hRate = Integer.parseInt(hRateStr);
                        CardiacRecord cardiacRecord = new CardiacRecord(name, sysBp, diaBp, hRate, comment);
                        DbHandler handler = new DbHandler(MainActivity.this);
                        handler.addRecord(cardiacRecord);
                        allRecords = handler.showRecords();
                        recyclerViewAdapter.setRecordList(allRecords);
                    dialog.dismiss();
                }
            }
        });
        dialog.getButton(Dialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }


    public void openDialog(CardiacRecord record){
        EditText editTextName;
        EditText editTextSys;
        EditText editTextDia;
        EditText editTextHr;
        EditText editTextComment;
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        View view = getLayoutInflater().inflate(R.layout.dialog_box, null);
        editTextName = view.findViewById(R.id.name_field);
        editTextSys = view.findViewById(R.id.systolic_field);
        editTextDia = view.findViewById(R.id.diastolic_field);
        editTextHr = view.findViewById(R.id.heart_rate_field);
        editTextComment = view.findViewById(R.id.comments_field);
        editTextName.setText(record.getName());
        editTextSys.setText(String.valueOf(record.getSystolicPressure()));
        editTextDia.setText(String.valueOf(record.getDiastolicPressure()));
        editTextHr.setText(String.valueOf(record.getHeartRate()));
        editTextComment.setText(record.getComment());
        builder.setView(view);
        dialog= builder.create();
        dialog.show();
        dialog.getButton(Dialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String sysBpStr = editTextSys.getText().toString();
                String diaBpStr = editTextDia.getText().toString();
                String hRateStr = editTextHr.getText().toString();
                String comment = editTextComment.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(sysBpStr) || TextUtils.isEmpty(diaBpStr) || TextUtils.isEmpty(hRateStr)){
                    if(TextUtils.isEmpty(name)){
                        editTextName.setError("Empty Field");
                        editTextName.setHint("Name Required");
                    }
                    if(TextUtils.isEmpty(sysBpStr)){
                        editTextSys.setError("Empty Field");
                        editTextSys.setHint("Systolic Pressure Required");
                    }
                    if ( TextUtils.isEmpty(diaBpStr)){
                        editTextDia.setError("Empty Field");
                        editTextDia.setHint("Diastolic Pressure Required");
                    }
                    if ( TextUtils.isEmpty(hRateStr)){
                        editTextHr.setError("empty");
                        editTextHr.setHint("Heart Rate Required");
                    }
                }


                else{
                    int sysBp = Integer.parseInt(sysBpStr);
                    int diaBp = Integer.parseInt(diaBpStr);
                    int hRate = Integer.parseInt(hRateStr);
                    record.setName(name);
                    record.setSystolicPressure(sysBp);
                    record.setDiastolicPressure(diaBp);
                    record.setHeartRate(hRate);
                    record.setComment(comment);
                    DbHandler handler = new DbHandler(MainActivity.this);
                    handler.updateRecord(record);
                    allRecords = handler.showRecords();
                    recyclerViewAdapter.setRecordList(allRecords);
                    dialog.dismiss();
                }
            }
        });
        dialog.getButton(Dialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}