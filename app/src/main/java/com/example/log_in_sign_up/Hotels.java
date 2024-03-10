package com.example.log_in_sign_up;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hotels extends AppCompatActivity {

    // Inside your Hotels activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        // Get a reference to the ListView
        ListView listView = findViewById(R.id.listView);
        ImageButton backButton = findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Apply the shadow animation
                Animation animation = AnimationUtils.loadAnimation(Hotels.this, R.anim.shadow_animation);
                backButton.startAnimation(animation);

                // Handle the click action (go back)
                onBackPressed();
            }
        });

        // Sample data for the list
        ArrayList<String> hotelList = new ArrayList<>();
        hotelList.add("Agathas Hotel powered by Cocotel");
        hotelList.add("Kehalani Beach Resort by CocotelOpens in new window");
        hotelList.add("RedDoorz La Sefa Hotel and Resort Atimonan");
        hotelList.add("Mango Suites Cauayan");
        hotelList.add("The Kroun Seafront Residences");

        // Create a custom adapter to bind the data to the ListView
        CustomAdapter adapter = new CustomAdapter(this, R.layout.hotel1_layout, hotelList);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);
    }

}
