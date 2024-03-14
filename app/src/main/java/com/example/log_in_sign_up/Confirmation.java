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
                selectedDateTextView.setText(selectedDate);
            }
        });


        // Retrieve data from Intent
        Intent intent = getIntent();
        String roomName = intent.getStringExtra("roomName");
        String hotelName = intent.getStringExtra("hotelName");
        String activityName = intent.getStringExtra("activityName");
        String location = intent.getStringExtra("location");
        String userName = intent.getStringExtra("userName");
        selectedDate = intent.getStringExtra("selectedDate");

        // Find TextViews in the layout file
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
        TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
        TextView activityNameTextView = findViewById(R.id.activityNameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView roomNameTextView = findViewById(R.id.textView2);

        // Set the text for each TextView
        selectedDateTextView.setText("Selected Date: " + selectedDate);
        hotelNameTextView.setText(hotelName);
        activityNameTextView.setText(activityName);
        locationTextView.setText(location);
        userNameTextView.setText(userName);
        roomNameTextView.setText(roomName);

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

                // Create Intent to navigate back to the previous activity (Detail1)
                Intent intent = new Intent(Confirmation.this, Hotels.class);
                startActivity(intent);

                // Finish the current activity
                finish();
            }
        });


        // Set the default selected date for CalendarView based on selectedDate
        if (selectedDate != null && !selectedDate.isEmpty()) {
            String[] dateParts = selectedDate.split("/");
            int month = Integer.parseInt(dateParts[0]) - 1;
            int day = Integer.parseInt(dateParts[1]);
            int year = Integer.parseInt(dateParts[2]);

            // Set the selected date for CalendarView
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, day);
            long selectedDateInMillis = calendar.getTimeInMillis();
            calendarView.setDate(selectedDateInMillis);
        }

            roomNameTextView.setText(roomName);
            hotelNameTextView.setText(hotelName);
            activityNameTextView.setText(activityName);
            locationTextView.setText(location);
            userNameTextView.setText(userName);
        }
    }

