package com.example.log_in_sign_up;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_IMAGE = 1;
    private String fullName;
    private String phoneNumber;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize databaseReference
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregister-4f25b-default-rtdb.firebaseio.com/");



        // Retrieve user information from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            fullName = extras.getString("FullName");
            phoneNumber = extras.getString("PhoneNumber");

            // Set user information in TextViews
            TextView fullNameTextView = findViewById(R.id.fullNameTextView);
            TextView phoneNumberTextView = findViewById(R.id.phoneNumberTextView);

            fullNameTextView.setText(fullName);
            phoneNumberTextView.setText(phoneNumber);


        }
    }}


