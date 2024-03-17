package com.example.log_in_sign_up;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String userName = extras.getString("userName");
            String hotelName = extras.getString("hotelName");
            String location = extras.getString("location");
            String activityName = extras.getString("activityName");
            String roomName = extras.getString("roomName");
            String selectedDate = extras.getString("selectedDate");

            // Display data in TextViews or any other UI components
            TextView userNameTextView = findViewById(R.id.userNameTextView);
            TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
            TextView locationTextView = findViewById(R.id.locationTextView);
            TextView activityNameTextView = findViewById(R.id.activityNameTextView);
            TextView roomNameTextView = findViewById(R.id.roomNameTextView);
            TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
            ImageView hotelImageView = findViewById(R.id.hotelImageView);

            userNameTextView.setText("User Name: " + userName);
            hotelNameTextView.setText("Hotel Name: " + hotelName);
            locationTextView.setText("Location: " + location);
            activityNameTextView.setText("Activity Name: " + activityName);
            roomNameTextView.setText("Room Name: " + roomName);
            selectedDateTextView.setText("Selected Date: " + selectedDate);

            // Set the hotel image based on hotel name
            if (hotelName.equals("Agathas Hotel powered by Cocotel")) {
                hotelImageView.setImageResource(R.drawable.h1);
            } else if (hotelName.equals("Kehalani Beach Resort by Cocotel")) {
                hotelImageView.setImageResource(R.drawable.h2);
            } else if (hotelName.equals("RedDoorz La Sefa Hotel and Resort Atimonan")) {
                hotelImageView.setImageResource(R.drawable.h3);
            } else if (hotelName.equals("Mango Suites Cauayan")) {
                hotelImageView.setImageResource(R.drawable.h4);
            } else if (hotelName.equals("The Kroun Seafront Residences")) {
                hotelImageView.setImageResource(R.drawable.h5);
            }
        }
    }
}
