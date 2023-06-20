package com.vetinfosys.vetinfosysmob;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.ViewHolder> {
    private List<String> timeSlots;

    public TimeSlotAdapter(List<String> timeSlots) {
        this.timeSlots = timeSlots;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_slot, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String timeSlot = timeSlots.get(position);
        holder.bind(timeSlot);
    }

    @Override
    public int getItemCount() {
        return timeSlots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTimeSlot;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTimeSlot = itemView.findViewById(R.id.tvTimeSlot);
        }

        public void bind(String timeSlot) {
            tvTimeSlot.setText(timeSlot);
        }
    }
}