package com.example.my_music;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        button = findViewById(R.id.logout);
        textView = findViewById(R.id.user_details);

        // Set a default welcome message instead of user details from a database
        textView.setText("Welcome to My Music App!");

        // Set up a click listener to navigate to Login activity when the button is clicked
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
