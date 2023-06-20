package com.vetinfosys.vetinfosysmob;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AppointmentActivity extends AppCompatActivity {
    private CalendarView calendarView;
    private GridView gridView;
    private CalendarAdapter calendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointmentscreen);

        // Initialize CalendarView
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                showTimeSlotsPopup(year, month, dayOfMonth);
            }
        });

        // Initialize GridView
        gridView = findViewById(R.id.calendarGridView);

        // Generate dates for the current month
        List<Date> dates = getDatesForCurrentMonth();

        // Generate availability for the dates (replace with your own logic)
        List<Boolean> availability = generateAvailability(dates);

        // Create and set the CalendarAdapter
        calendarAdapter = new CalendarAdapter(dates, availability);
        gridView.setAdapter(calendarAdapter);
    }

    private List<Date> getDatesForCurrentMonth() {
        List<Date> dates = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        for (int i = 0; i < daysInMonth; i++) {
            Date date = calendar.getTime();
            dates.add(date);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dates;
    }

    private List<Boolean> generateAvailability(List<Date> dates) {
        // Replace this with your own logic to generate availability for the dates
        // For simplicity, let's assume all dates are available
        List<Boolean> availability = new ArrayList<>();
        for (Date date : dates) {
            availability.add(true);
        }
        return availability;
    }

    private void showTimeSlotsPopup(int year, int month, int dayOfMonth) {
        // Retrieve the time slots for the selected date from your database or any other data source
        List<String> timeSlots = getTimeSlots(year, month, dayOfMonth);

        // Convert the list of time slots to an array
        final String[] timeSlotsArray = timeSlots.toArray(new String[timeSlots.size()]);

        // Create and show the pop-up dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select a Time Slot")
                .setItems(timeSlotsArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String selectedTimeSlot = timeSlotsArray[which];
                        Toast.makeText(AppointmentActivity.this, "Selected Time Slot: " + selectedTimeSlot, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private List<String> getTimeSlots(int year, int month, int dayOfMonth) {
        // Retrieve the time slots for the selected date from your database or any other data source
        // This is a placeholder method, replace it with your own logic

        // For simplicity, let's assume we have some predefined time slots
        List<String> timeSlots = new ArrayList<>();
        timeSlots.add("9:00 AM");
        timeSlots.add("10:00 AM");
        timeSlots.add("11:00 AM");
        timeSlots.add("12:00 PM");
        timeSlots.add("1:00 PM");
        timeSlots.add("2:00 PM");
        timeSlots.add("3:00 PM");
        timeSlots.add("4:00 PM");
        timeSlots.add("5:00 PM");
        timeSlots.add("6:00 PM");

        return timeSlots;
    }