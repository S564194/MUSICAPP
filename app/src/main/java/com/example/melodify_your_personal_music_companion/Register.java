package com.example.melodify_your_personal_music_companion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    // UI Elements
    private TextInputEditText inputEmail, inputPassword;
    private Button registerButton;
    private ProgressBar loadingIndicator;
    private TextView loginLink;

    // Firebase Authentication
    private FirebaseAuth firebaseAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check for an already signed-in user
        FirebaseUser loggedInUser = firebaseAuth.getCurrentUser();
        if (loggedInUser != null) {
            moveToSplashScreen();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Link UI components
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        registerButton = findViewById(R.id.btn_register);
        loadingIndicator = findViewById(R.id.progressBar);
        loginLink = findViewById(R.id.loginNow);

        // Navigate to login screen
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToLoginScreen();
            }
        });

        // Handle registration
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingIndicator.setVisibility(View.VISIBLE);

                String email = inputEmail.getText() != null ? inputEmail.getText().toString().trim() : "";
                String password = inputPassword.getText() != null ? inputPassword.getText().toString().trim() : "";

                if (isInputValid(email, password)) {
                    createAccount(email, password);
                } else {
                    loadingIndicator.setVisibility(View.GONE);
                }
            }
        });
    }

    private boolean isInputValid(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            displayToastMessage("Please provide a valid email.");
            return false;
        }

        if (TextUtils.isEmpty(password)) {
            displayToastMessage("Password cannot be empty.");
            return false;
        }

        return true;
    }

    private void createAccount(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        loadingIndicator.setVisibility(View.GONE);

                        if (task.isSuccessful()) {
                            displayToastMessage("Registration successful!");
                            moveToLoginScreen();
                        } else {
                            displayToastMessage("Failed to register. Please try again.");
                        }
                    }
                });
    }

    private void moveToLoginScreen() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }

    private void moveToSplashScreen() {
        Intent intent = new Intent(Register.this, SplashScreenActivity.class);
        startActivity(intent);
        finish();
    }

    private void displayToastMessage(String message) {
        Toast.makeText(Register.this, message, Toast.LENGTH_SHORT).show();
    }
}
