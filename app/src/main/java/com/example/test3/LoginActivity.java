package com.example.test3;// LoginActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword,editextEmail;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        editextEmail = findViewById(R.id.)
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle login logic here
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (isValidCredentials(username, password)) {
                    // Login successful, you can navigate to another activity or perform other actions
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Login failed, show an error message
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // You can implement your own validation logic here
    private boolean isValidCredentials(String username, String password) {
        // For simplicity, let's say login is successful if both fields are non-empty
        return !username.isEmpty() && !password.isEmpty();
    }
}