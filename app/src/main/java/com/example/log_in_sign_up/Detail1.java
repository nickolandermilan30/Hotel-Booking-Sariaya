package com.example.log_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

public class Detail1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail1);

        ImageButton backButton = findViewById(R.id.back);

        Intent intent = getIntent();
        String fullName = intent.getStringExtra("FullName");

        // Display the user's full name in a TextView or any other UI component
        TextView fullNameTextView = findViewById(R.id.fullNameTextView);
        fullNameTextView.setText(fullName);

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

        // Get the extras from the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hotelName = extras.getString("hotelName");
            int hotelImageId1 = extras.getInt("hotelImage1");
            int hotelImageId2 = extras.getInt("hotelImage2");
            String customText = extras.getString("customText");
            String customText2 = extras.getString("customText2");
            String customText3 = extras.getString("customText3");
            String customText4 = extras.getString("customText4");


            // Set the hotel details in the UI
            TextView hotelNameTextView = findViewById(R.id.hotelName);
            TextView customTextView = findViewById(R.id.activityName);
            TextView customTextView2 = findViewById(R.id.location);
            TextView customTextView3 = findViewById(R.id.cancelation);
            TextView customTextView4 = findViewById(R.id.about);
            Button bookNowButton = findViewById(R.id.bookNowButton);

            hotelNameTextView.setText(hotelName);
            customTextView.setText(customText);
            customTextView2.setText(customText2);
            customTextView3.setText(customText3);
            customTextView4.setText(customText4);

            // Set up ViewPager2
            ViewPager2 viewPager2 = findViewById(R.id.viewPager);
            ImagePagerAdapter adapter = new ImagePagerAdapter(this, new int[]{hotelImageId1, hotelImageId2});
            viewPager2.setAdapter(adapter);

           // Set onClickListener for the "Book Now" button
            bookNowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create an Intent to navigate to ConfirmActivity
                    Intent intent = new Intent(Detail1.this, Confirmation.class);

                    // Pass necessary data to ConfirmActivity
                    intent.putExtra("hotelName", hotelName);
                    intent.putExtra("activityName", customText); // Custom text from extras
                    intent.putExtra("location", customText2); // Custom text 2 from extras

                    // Pass the user's full name to Confirmation activity
                    intent.putExtra("userName", fullName);

                    // Start ConfirmActivity
                    startActivity(intent);
                }
            });
        }
    }
}
