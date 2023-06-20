package com.vetinfosys.vetinfosysmob;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);

        // Initialize views
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        // Set click listener for login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Perform login authentication (replace with your own logic)
                if (isValidCredentials(username, password)) {
                    // Successful login, navigate to the next activity
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Prevents going back to the login screen using the back button
                } else {
                    // Invalid login, show an error message
                    showToast("Invalid username or password");
                }
            }
        });
    }

    private boolean isValidCredentials(String username, String password) {
        // Implement your own authentication logic (e.g., check against your database)
        // Return true if the credentials are valid, false otherwise
        return username.equals("admin") && password.equals("password");
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}