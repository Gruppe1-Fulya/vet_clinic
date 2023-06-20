package com.vetinfosys.vetinfosysmob;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends Activity {


    private Button btnBookAppointment;
    private Button btnViewPatientInfo;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Initialize buttons
        btnBookAppointment = findViewById(R.id.btnBookAppointment);
        btnViewPatientInfo = findViewById(R.id.btnViewPatientInfo);
        btnLogout = findViewById(R.id.btnLogout);

        // Set click listeners for buttons
        btnBookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on "Book Appointment" button
                // Navigate to the appointment booking screen
                Intent intent = new Intent(HomeActivity.this, AppointmentActivity.class);
                startActivity(intent);
            }
        });

        btnViewPatientInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on "View Patient Information" button
                // Navigate to the patient information screen
                Intent intent = new Intent(HomeActivity.this, PatientInfoActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click on "Logout" button
                // Perform logout and navigate to the login screen
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Optional: Prevents going back to the home screen using the back button
            }
        });
    }
}
