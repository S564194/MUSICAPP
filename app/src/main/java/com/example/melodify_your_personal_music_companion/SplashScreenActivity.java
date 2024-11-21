package com.example.melodify_your_personal_music_companion;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreenActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;
    private Button logoutButton;
    private TextView userDetailsTextView;
    private ImageView continueImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize FirebaseAuth instance
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        logoutButton = findViewById(R.id.logout);
        userDetailsTextView = findViewById(R.id.user_details);
        continueImageView = findViewById(R.id.melodify_Image);

        // Check if the user is logged in
        currentUser = firebaseAuth.getCurrentUser();

        if (currentUser == null) {
            // Redirect to Login activity if user is not logged in
            navigateToLogin();
        } else {
            // Display user email if logged in
            userDetailsTextView.setText(currentUser.getEmail());
        }

        // Set up logout button click listener
        logoutButton.setOnClickListener(v -> logoutUser());

        // Set up continue button click listener to navigate to MainActivity
        continueImageView.setOnClickListener(v -> navigateToMain());
    }

    private void logoutUser() {
        firebaseAuth.signOut();
        navigateToLogin();
    }

    private void navigateToLogin() {
        Intent loginIntent = new Intent(SplashScreenActivity.this, Login.class);
        startActivity(loginIntent);
        finish();
    }

    private void navigateToMain() {
        Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }
}
