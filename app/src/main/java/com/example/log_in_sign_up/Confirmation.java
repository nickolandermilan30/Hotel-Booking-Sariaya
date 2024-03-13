package com.example.log_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Confirmation extends AppCompatActivity {

    private CalendarView calendarView;

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
               String selectedDate = (month + 1) + "/" +  dayOfMonth + "/" + year;
                Toast.makeText(Confirmation.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pumunta sa Room activity kapag pindutin ang button na "Room"
                Intent intent = new Intent(Confirmation.this, RoomActivity.class);
                startActivity(intent);
            }
        });

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
            String roomName = extras.getString("roomName");
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
            TextView roomNameTextView = findViewById(R.id.textView2);

            roomNameTextView.setText(roomName);
            hotelNameTextView.setText(hotelName);
            activityNameTextView.setText(activityName);
            locationTextView.setText(location);
            userNameTextView.setText(userName);

        }
    }
}
