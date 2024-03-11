package com.example.log_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hotels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        ListView listView = findViewById(R.id.listView);
        ImageButton backButton = findViewById(R.id.back);

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

        // Sample data for the list
        ArrayList<String> hotelNames = new ArrayList<>();
        hotelNames.add("Agathas Hotel powered by Cocotel");
        hotelNames.add("Kehalani Beach Resort by Cocotel");
        hotelNames.add("RedDoorz La Sefa Hotel and Resort Atimonan");
        hotelNames.add("Mango Suites Cauayan");
        hotelNames.add("The Kroun Seafront Residences");

        // Sample image resources for the hotels
        int[] hotelImages1 = {
                R.drawable.h1,
                R.drawable.h2,
                R.drawable.h3,
                R.drawable.h4,
                R.drawable.h5
        };

        int[] hotelImages2 = {
                R.drawable.m1,
                R.drawable.m2,
                R.drawable.m3,
                R.drawable.m4,
                R.drawable.m5
        };

        // Custom text for each item
        String[] customTexts = {
                "₱1,386 - ₱1,728",
                "₱4,253 - ₱4,958",
                "₱ 1,157",
                "₱1,145 - ₱1,228",
                "₱1,145 - ₱1,228"
        };

        String[] customTexts2 = {
                "Agatha's Hotel Gen, Yngente St, Lopez, 4316 Quezon",
                "Bali-Balic, Perez Rd, Pinagtubigan, Perez, Quezon",
                "Pan Philippine Highway, Barangay Balubad XWPX+GQ8 Asian Highway 26, Atimonan, 4331 Quezon",
                "Quezon St, Cauayan City, 3305 Isabela",
                "San Juan - Laiya Rd, San Juan, Batangas"
        };

        String[] customTexts3 = {
                "Free Cancelation",
                "Free Cancelation",
                "",
                "Free Cancelation",
                "Free Cancelation"
        };

        String[] customTexts4 = {
                "Agathas Hotel powered by Cocotel offers air-conditioned accommodation in Quezon. Boasting room service, this property also provides guests with a restaurant. There is a year-round outdoor pool and guests can make use of free WiFi and free private parking.\n" +
                        "\n" +
                        "At the hotel, all rooms include a desk. All guest rooms feature a private bathroom, free toiletries and bed linen.\n" +
                        "\n" +
                        "The reception at Agathas Hotel powered by Cocotel can provide tips on the area.\n" +
                        "\n" +
                        "The nearest airport is Naga Airport, 179 km from the accommodation.",

                "Situated on the beachfront in Quezon, Kehalani Beach Resort by Cocotel has a garden. The accommodation features room service and a concierge service for guests.\n" +
                        "\n" +
                        "Featuring a private bathroom with a shower and free toiletries, rooms at the resort also boast a sea view. The rooms have bed linen.\n" +
                        "\n" +
                        "The nearest airport is Ninoy Aquino International Airport, 186 km from Kehalani Beach Resort by Cocotel.",

                "RedDoorz La Sefa Hotel and Resort Atimonan provides accommodation in Atimonan. This 2-star hotel offers a 24-hour front desk. The hotel also features free WiFi and free private parking.\n" +
                        "\n" +
                        "At the hotel, rooms have air conditioning and a flat-screen TV.\n" +
                        "\n" +
                        "The nearest airport is Ninoy Aquino International Airport, 165 km from RedDoorz La Sefa Hotel and Resort Atimonan.",
                "Mango Suites Cauayan provides accommodation in Cauayan City. Among the facilities of this property are a restaurant, free shuttle service and room service, along with free WiFi throughout the property. Free private parking is available and the hotel also features car hire for guests who want to explore the surrounding area.\n" +
                        "\n" +
                        "At the hotel, rooms have a wardrobe. Complete with a private bathroom fitted with a bidet and free toiletries, all rooms at Mango Suites Cauayan have a flat-screen TV and air conditioning, and some rooms also boast a terrace. At the accommodation the rooms are fitted with bed linen and towels.\n" +
                        "\n" +
                        "Staff speak English and Filipino at the 24-hour front desk.\n" +
                        "\n" +
                        "The nearest airport is Ninoy Aquino International Airport, 170 km from Mango Suites Cauayan.\n" +
                        "\n" +
                        "Couples particularly like the location — they rated it 8.9 for a two-person trip.",
                "Set in Agdañgan, The Kroun Seafront Residences features a shared lounge. There is a terrace and guests can make use of free WiFi and free private parking.\n" +
                        "\n" +
                        "At the resort every room has a private bathroom and bed linen.\n" +
                        "\n" +
                        "Guests at The Kroun Seafront Residences will be able to enjoy activities in and around Agdañgan, like cycling.\n" +
                        "\n" +
                        "The nearest airport is Ninoy Aquino International Airport, 168 km from the accommodation."
        };


        // Custom adapter to bind data to the ListView
        CustomAdapter adapter = new CustomAdapter(this, R.layout.hotel1_layout, customTexts4, customTexts3, customTexts2, hotelNames, hotelImages1, hotelImages2, customTexts);

        // Set the adapter to the ListView
        listView.setAdapter(adapter);

        // Set an item click listener for the ListView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Create an Intent to start the details activity
                Intent intent = new Intent(Hotels.this, Detail1.class);

                // Pass hotel details using Intent extras
                intent.putExtra("hotelName", hotelNames.get(position));
                intent.putExtra("hotelImage1", hotelImages1[position]);
                intent.putExtra("hotelImage2", hotelImages2[position]);
                intent.putExtra("customText", customTexts[position]);
                intent.putExtra("customText2", customTexts2[position]);
                intent.putExtra("customText3", customTexts3[position]);
                intent.putExtra("customText4", customTexts4[position]);

                // Start the details activity
                startActivity(intent);
            }
        });
    }
}
