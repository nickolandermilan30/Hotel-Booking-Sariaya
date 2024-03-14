package com.example.log_in_sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Retrieve data from Intent
        String userName = getIntent().getStringExtra("userName");
        String hotelName = getIntent().getStringExtra("hotelName");
        String location = getIntent().getStringExtra("location");
        String activityName = getIntent().getStringExtra("activityName");
        String roomName = getIntent().getStringExtra("roomName");
        String selectedDate = getIntent().getStringExtra("selectedDate");

        // Find TextViews in the layout file
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView activityNameTextView = findViewById(R.id.activityNameTextView);
        TextView roomNameTextView = findViewById(R.id.roomNameTextView);
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);

        // Set the text for each TextView
        userNameTextView.setText("User Name: " + userName);
        hotelNameTextView.setText("Hotel Name: " + hotelName);
        locationTextView.setText("Location: " + location);
        activityNameTextView.setText("Activity Name: " + activityName);
        roomNameTextView.setText("Room Name: " + roomName);
        selectedDateTextView.setText("Selected Date: " + selectedDate);
    }
}
