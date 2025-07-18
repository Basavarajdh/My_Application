package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText etUsername, etNewPassword;
    private Button btnReset;
    private DbHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        etUsername = findViewById(R.id.etUsernameReset);
        etNewPassword = findViewById(R.id.etNewPassword);
        btnReset = findViewById(R.id.btnResetPassword);
        dbHandler = new DbHandler(this);

        btnReset.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String newPassword = etNewPassword.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(newPassword)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            String hashedPassword = LoginActivity.hashPassword(newPassword);
            boolean success = dbHandler.updatePassword(username, hashedPassword);

            if (success) {
                Toast.makeText(this, "Password updated", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
