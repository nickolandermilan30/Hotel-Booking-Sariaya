package com.example.log_in_sign_up;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Detail1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        // Get the extras from the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hotelName = extras.getString("hotelName");
            String activityName = extras.getString("activityName");
            int hotelImageId = extras.getInt("hotelImage");

            // Set the hotel details in the UI
            TextView hotelNameTextView = findViewById(R.id.hotelName);
            TextView activityNameTextView = findViewById(R.id.activityName);
            ImageView hotelImageView = findViewById(R.id.hotelImage);

            hotelNameTextView.setText(hotelName);
            activityNameTextView.setText(activityName);
            hotelImageView.setImageResource(hotelImageId);
        }
    }
}