package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    private Button loginButton;
    private EditText emailInput, passwordInput;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);  // Changed to activity_login

        initializeViews();
        setupDatabase();
        setupLoginButton();
    }

    private void initializeViews() {
        loginButton = findViewById(R.id.btnLogin);
        emailInput = findViewById(R.id.etEmailLogin);
        passwordInput = findViewById(R.id.etPasswordLogin);
    }

    private void setupDatabase() {
        dbHandler = new DbHandler(this);
        // Only add test user if it doesn't exist
        if (dbHandler.checkUser(new User("Basavaraj", hashPassword("1234"))) == -1) {
            dbHandler.addUser(new User("Basavaraj", hashPassword("1234")));
        }
    }

    private void setupLoginButton() {
        loginButton.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        if (validateInput(email, password)) {
            performLogin(email, password);
        }
    }

    private boolean validateInput(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password cannot be empty");
            return false;
        }
        return true;
    }

    private void performLogin(String email, String password) {
        String hashedPassword = hashPassword(password);
        int userId = dbHandler.checkUser(new User(email, hashedPassword));

        if (userId != -1) {
            startMainActivity();
        } else {
            showLoginError();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void showLoginError() {
        Snackbar.make(
                findViewById(android.R.id.content),
                "Invalid email or password",
                Snackbar.LENGTH_LONG
        ).show();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
