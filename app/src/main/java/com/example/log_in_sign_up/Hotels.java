package com.example.log_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hotels extends AppCompatActivity {

    // Inside your Hotels activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        ListView listView = findViewById(R.id.listView);

        // Sample data for the list
        ArrayList<String> hotelNames = new ArrayList<>();
        hotelNames.add("Agathas Hotel powered by Cocotel");
        hotelNames.add("Kehalani Beach Resort by Cocotel");
        hotelNames.add("RedDoorz La Sefa Hotel and Resort Atimonan");
        hotelNames.add("Mango Suites Cauayan");
        hotelNames.add("The Kroun Seafront Residences");

        // Sample image resources for the hotels
        int[] hotelImages = {
                R.drawable.h1,
                R.drawable.h2,
                R.drawable.h3,
                R.drawable.h4,
                R.drawable.h5
        };

        CustomAdapter adapter = new CustomAdapter(this, R.layout.hotel1_layout, hotelNames, hotelImages);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set an item click listener for the ListView
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Create an Intent to start the details activity
            Intent intent = new Intent(Hotels.this, Detail1.class);

            // Append the position to the activity name
            String activityName = "DetailsActivity" + (position + 1);

            // Pass hotel details using Intent extras
            intent.putExtra("hotelName", hotelNames.get(position));
            intent.putExtra("activityName", activityName);
            intent.putExtra("hotelImage", hotelImages[position]);

            // Start the details activity
            startActivity(intent);
        });
    }

}
