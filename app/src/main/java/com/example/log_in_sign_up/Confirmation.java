package com.example.log_in_sign_up;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Confirmation extends AppCompatActivity {

    private CalendarView calendarView;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        ImageButton doneButton = findViewById(R.id.done);
        ImageButton backButton = findViewById(R.id.back);
        ImageButton roomButton = findViewById(R.id.room);
        calendarView = findViewById(R.id.calendarView);

        // Find TextViews in the layout file
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
        TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
        TextView activityNameTextView = findViewById(R.id.activityNameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView roomNameTextView = findViewById(R.id.textView2);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String roomName = intent.getStringExtra("roomName");
        String hotelName = intent.getStringExtra("hotelName");
        String activityName = intent.getStringExtra("activityName");
        String location = intent.getStringExtra("location");
        String userName = intent.getStringExtra("userName");
        selectedDate = intent.getStringExtra("selectedDate");
        int hotelImageId1 = intent.getIntExtra("hotelImageId1", 0); // Retrieve hotelImageId1

        // Set the text for each TextView
        selectedDateTextView.setText("Selected Date: " + selectedDate);
        hotelNameTextView.setText(hotelName);
        activityNameTextView.setText(activityName);
        locationTextView.setText(location);
        userNameTextView.setText(userName);
        roomNameTextView.setText(roomName);

        // Set the hotel image based on hotelImageId1
        ImageView hotelImageView = findViewById(R.id.hotelImageView);
        hotelImageView.setImageResource(hotelImageId1);


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

        // I-set ang OnClickListener para sa "Done" button
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Suriin kung kumpleto na ang mga detalye
                if (selectedDate == null || selectedDate.isEmpty() || roomName == null || roomName.isEmpty()) {
                    // Alert dialog for incomplete details
                    AlertDialog.Builder builder = new AlertDialog.Builder(Confirmation.this);
                    builder.setMessage("You need to complete the list.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    // Apply the animation
                    Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shadow_animation);
                    hotelImageView.startAnimation(animation);

                    // Delay the navigation to ListActivity to allow time for animation
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    // Create an Intent to navigate to ListActivity
                                    Intent intent = new Intent(Confirmation.this, ListActivity.class);

                                    // Add all necessary data to the Intent
                                    intent.putExtra("userName", userName);
                                    intent.putExtra("hotelName", hotelName);
                                    intent.putExtra("location", location);
                                    intent.putExtra("activityName", activityName);
                                    intent.putExtra("roomName", roomName);
                                    intent.putExtra("selectedDate", selectedDate);
                                    intent.putExtra("hotelImageId1", hotelImageId1); // Include hotelImageId1

                                    // Start ListActivity
                                    startActivity(intent);
                                }
                            },
                            500 // Set delay in milliseconds
                    );
                }
            }
        });



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

