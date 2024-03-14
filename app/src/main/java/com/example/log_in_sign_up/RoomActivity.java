package com.example.log_in_sign_up;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        // Retrieve the selected date from the Intent
        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("selectedDate");

        // Display the selected date in a TextView
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
        selectedDateTextView.setText(selectedDate);

        // Get the extras from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String roomName = extras.getString("roomName");
            String hotelName = extras.getString("hotelName");
            String activityName = extras.getString("activityName");
            String location = extras.getString("location");
            String userName = extras.getString("userName");

            // Find TextViews in the layout file
            TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
            TextView activityNameTextView = findViewById(R.id.activityNameTextView);
            TextView locationTextView = findViewById(R.id.locationTextView);
            TextView userNameTextView = findViewById(R.id.userNameTextView);

            // Set the text for each TextView
            hotelNameTextView.setText(hotelName);
            activityNameTextView.setText(activityName);
            locationTextView.setText(location);
            userNameTextView.setText(userName);
        }
    }

    public void highlightButton(View view) {
        final Button button = (Button) view;

        // Get the room name from the clicked button
        String roomName = button.getText().toString();

        // Retrieve the selected date from the Intent
        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("selectedDate");

        // Get the extras from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hotelName = extras.getString("hotelName");
            String activityName = extras.getString("activityName");
            String location = extras.getString("location");
            String userName = extras.getString("userName");

            // Pass the room name and other details to Confirmation activity
            Intent confirmationIntent = new Intent(RoomActivity.this, Confirmation.class);
            confirmationIntent.putExtra("roomName", roomName);
            confirmationIntent.putExtra("hotelName", hotelName);
            confirmationIntent.putExtra("activityName", activityName);
            confirmationIntent.putExtra("location", location);
            confirmationIntent.putExtra("userName", userName);
            confirmationIntent.putExtra("selectedDate", selectedDate);
            startActivity(confirmationIntent);
        }

        button.setBackgroundColor(Color.RED);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.TRANSPARENT);
            }
        });
    }
}
