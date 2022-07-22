package com.example.cardiacrecorder.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardiacrecorder.MainActivity;
import com.example.cardiacrecorder.R;
import com.example.cardiacrecorder.ShowActivity;
import com.example.cardiacrecorder.model.CardiacRecord;

import java.util.List;

/**
 * This class is for Recycler View
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<CardiacRecord> recordList;
    private Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent,false);
        return new ViewHolder(view);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        CardiacRecord record= recordList.get(position);
        holder.record_name.setText(record.getName());
        holder.blood_pressure.setText(record.getSystolicPressure()+"/"+record.getDiastolicPressure());
        holder.heart_rate.setText(String.valueOf(record.getHeartRate()));
        holder.date.setText(record.getDate());
    }

    /**
     *
     * @return the size of record list
     */
    @Override
    public int getItemCount() {
        return recordList.size();
    }

    public void setRecordList( List<CardiacRecord> recordList){
        this.recordList= recordList;
        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView record_name;
        public TextView blood_pressure;
        public TextView heart_rate;
        public TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            record_name= itemView.findViewById(R.id.name);
            blood_pressure= itemView.findViewById(R.id.bp);
            heart_rate= itemView.findViewById(R.id.heart_rate);
            date= itemView.findViewById(R.id.date);
        }

        @Override
        public void onClick(View v) {
            int position= getAbsoluteAdapterPosition();
            Intent intent = new Intent(context, ShowActivity.class);
            CardiacRecord cardiacRecord= recordList.get(position);
            intent.putExtra("name", cardiacRecord.getName());
            intent.putExtra("date", cardiacRecord.getDate());
            intent.putExtra("time", cardiacRecord.getTime());
            intent.putExtra("systolicPressure", String.valueOf(cardiacRecord.getSystolicPressure()));
            intent.putExtra("diastolicPressure", String.valueOf(cardiacRecord.getDiastolicPressure()));
            intent.putExtra("heartRate", String.valueOf(cardiacRecord.getHeartRate()));
            intent.putExtra("comment", cardiacRecord.getComment());
            context.startActivity(intent);
        }
    }
}
