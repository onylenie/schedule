package com.example.testapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.ui.Day;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.DayViewHolder> {

    private List<Day> days;

    public CalendarAdapter(List<Day> days) {
        this.days = days;
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView tvDayOfWeek;
        TextView tvDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDayOfWeek = itemView.findViewById(R.id.tvDayOfWeek);
            tvDay = itemView.findViewById(R.id.tvDay);
        }
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendary_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = days.get(position);
        holder.tvDayOfWeek.setText(day.getDayOfWeek());
        holder.tvDay.setText(String.valueOf(day.getDayNumber()));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }
}



