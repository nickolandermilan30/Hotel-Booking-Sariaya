package com.example.log_in_sign_up;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        ImageButton backButton = findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Apply the animation
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shadow_animation);
                backButton.startAnimation(animation);

                // Handle back button click (e.g., navigate back to the previous activity)
                onBackPressed();
            }
        });

        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hotelName = extras.getString("hotelName");
            String activityName = extras.getString("activityName");
            String location = extras.getString("location");

            // Retrieve user name from Intent
            String userName = extras.getString("userName");

            // Display data in TextViews or any other UI components
            TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
            TextView activityNameTextView = findViewById(R.id.activityNameTextView);
            TextView locationTextView = findViewById(R.id.locationTextView);
            TextView userNameTextView = findViewById(R.id.userNameTextView);

            hotelNameTextView.setText(hotelName);
            activityNameTextView.setText(activityName);
            locationTextView.setText(location);
            userNameTextView.setText(userName);

            // Set other data to respective TextViews
        }
    }
}
