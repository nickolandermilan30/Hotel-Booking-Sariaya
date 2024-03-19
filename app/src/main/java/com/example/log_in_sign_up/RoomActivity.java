package com.example.log_in_sign_up;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RoomActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        // Retrieve the selected date from the Intent
        Intent intent = getIntent();
        String selectedDate = intent.getStringExtra("selectedDate");
        ImageButton backButton = findViewById(R.id.back);

        ImageButton homeButton = findViewById(R.id.b1);
        ImageButton roomButton = findViewById(R.id.b2);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pumunta sa Room activity kapag pindutin ang button na "Room"
                Intent intent = new Intent(RoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pumunta sa Room activity kapag pindutin ang button na "Room"
                Intent intent = new Intent(RoomActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });

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

            // Hide TextViews
            hotelNameTextView.setVisibility(View.GONE);
            activityNameTextView.setVisibility(View.GONE);
            locationTextView.setVisibility(View.GONE);
            selectedDateTextView.setVisibility(View.GONE);
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

        // Change the background color of the button without changing its shape
        Drawable background = button.getBackground();
        if (background instanceof ShapeDrawable) {
            ((ShapeDrawable) background).getPaint().setColor(Color.RED);
        } else if (background instanceof GradientDrawable) {
            ((GradientDrawable) background).setColor(Color.RED);
        } else if (background instanceof ColorDrawable) {
            ((ColorDrawable) background).setColor(Color.RED);
        }


    }
}
