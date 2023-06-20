package com.vetinfosys.vetinfosysmob;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class CalendarAdapter extends BaseAdapter {
    private List<Date> dates;
    private List<Boolean> availability;

    public CalendarAdapter(List<Date> dates, List<Boolean> availability) {
        this.dates = dates;
        this.availability = availability;
    }

    @Override
    public int getCount() {
        return dates.size();
    }

    @Override
    public Object getItem(int position) {
        return dates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_slot, parent, false);
        }

        TextView tvDate = convertView.findViewById(R.id.tvDate);
        Date date = dates.get(position);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        tvDate.setText(String.valueOf(day));

        if (availability.get(position)) {
            convertView.setBackgroundColor(getResources().getColor(R.color.green));
        } else {
            convertView.setBackgroundColor(getResources().getColor(R.color.red));
        }

        return convertView;
    }
}
}
