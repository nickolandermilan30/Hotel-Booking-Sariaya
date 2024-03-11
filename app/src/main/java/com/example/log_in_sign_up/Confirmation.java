package com.example.log_in_sign_up;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

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
