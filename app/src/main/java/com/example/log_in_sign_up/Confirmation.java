package com.example.log_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Confirmation extends AppCompatActivity {

    private CalendarView calendarView;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);


        ImageButton backButton = findViewById(R.id.back);
        ImageButton roomButton = findViewById(R.id.room);
        calendarView = findViewById(R.id.calendarView);

        // I-set ang OnDateChangeListener sa CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = (month + 1) + "/" +  dayOfMonth + "/" + year; // Set the selected date

                // Update the TextView to display the selected date
                TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
                selectedDateTextView.setText("Selected Date: " + selectedDate);
            }
        });


        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String roomName = extras.getString("roomName");
            String hotelName = extras.getString("hotelName");
            String activityName = extras.getString("activityName");
            String location = extras.getString("location");
            String userName = extras.getString("userName");

            // Set OnClickListener for roomButton
            roomButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create Intent to navigate to RoomActivity
                    Intent intent = new Intent(Confirmation.this, RoomActivity.class);

                    // Pass the necessary details as extras in the Intent
                    intent.putExtra("roomName", roomName);
                    intent.putExtra("hotelName", hotelName);
                    intent.putExtra("activityName", activityName);
                    intent.putExtra("location", location);
                    intent.putExtra("userName", userName);
                    intent.putExtra("selectedDate", selectedDate);

                    // Start the RoomActivity
                    startActivity(intent);
                }
            });

            // Set OnClickListener for backButton
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

            // Display data in TextViews or any other UI components
            TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
            TextView activityNameTextView = findViewById(R.id.activityNameTextView);
            TextView locationTextView = findViewById(R.id.locationTextView);
            TextView userNameTextView = findViewById(R.id.userNameTextView);
            TextView roomNameTextView = findViewById(R.id.textView2);

            roomNameTextView.setText(roomName);
            hotelNameTextView.setText(hotelName);
            activityNameTextView.setText(activityName);
            locationTextView.setText(location);
            userNameTextView.setText(userName);
        }
        // Set the default selected date for CalendarView to the current date
        Calendar calendar = Calendar.getInstance();
        long currentDate = calendar.getTimeInMillis();
        calendarView.setDate(currentDate);

    }
}
