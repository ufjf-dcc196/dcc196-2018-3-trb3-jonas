package com.example.gabrielmaia.chocolapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import model.History;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<History> histories;
    private History currentHistory;

    public HistoryAdapter(ArrayList<History> histories) {
        this.histories = histories;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_item, viewGroup, false);
        return new HistoryViewHolder(view);
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView candyName;
        public TextView candyPrice;
        public TextView candyMonthYear;
        public TextView candyDay;
        public TextView candyHour;
        public TextView candyType;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            candyName =  itemView.findViewById(R.id.h_name);
            candyPrice =  itemView.findViewById(R.id.h_price);
            candyType =  itemView.findViewById(R.id.h_type);
            candyMonthYear =  itemView.findViewById(R.id.h_month);
            candyDay =  itemView.findViewById(R.id.h_day);
            candyHour =  itemView.findViewById(R.id.h_hour);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder viewHolder, int i) {
        currentHistory = this.histories.get(i);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Calendar calendar = Calendar.getInstance();
            Date date = format.parse(currentHistory.getDate());
            calendar.setTime(date);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            viewHolder.candyDay.setText(pad(String.valueOf(day)));
            viewHolder.candyHour.setText(pad(String.valueOf(hour)) + ":" + pad(String.valueOf(minute)));
            viewHolder.candyMonthYear.setText(pad(String.valueOf(month)) + "/" + String.valueOf(year));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        viewHolder.candyName.setText(currentHistory.getName());
        viewHolder.candyType.setText(currentHistory.getType());
        viewHolder.candyPrice.setText(currentHistory.getPrice());

    }

    private String pad(String s) {
        DecimalFormat mFormat= new DecimalFormat("00");
        s = mFormat.format(Double.valueOf(s));
        return s;
    }

    @Override
    public int getItemCount() {
        return this.histories.size();
    }

    public void addHistory(History sell){
        this.histories.add(sell);
        notifyItemInserted(getItemCount());
    }
}
