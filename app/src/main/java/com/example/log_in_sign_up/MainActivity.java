package com.example.log_in_sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
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

            // Initialize logout button and set click listener
            ImageButton logoutButton = findViewById(R.id.logout);
            logoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Display alert dialogue for confirmation
                    showLogoutConfirmationDialog();
                }
            });


        }
    }

    private void showLogoutConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout for the dialog
        View customLayout = getLayoutInflater().inflate(R.layout.custom_logout_dialog, null);
        builder.setView(customLayout);

        // Find views in the custom layout
        TextView titleTextView = customLayout.findViewById(R.id.dialogTitle);
        TextView messageTextView = customLayout.findViewById(R.id.dialogMessage);
        Button yesButton = customLayout.findViewById(R.id.btnYes);
        Button noButton = customLayout.findViewById(R.id.btnNo);

        // Set custom title and message
        titleTextView.setText("Logout");
        messageTextView.setText("Are you sure you want to logout?");

        // Create the custom dialog
        AlertDialog dialog = builder.create();

        // Set click listeners for buttons
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // User confirmed logout, sign out and navigate to login screen
                FirebaseAuth.getInstance().signOut();
                navigateToLoginScreen();
                dialog.dismiss();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // User cancelled logout, dismiss the dialogue
                dialog.dismiss();
            }
        });

        // Show the custom dialog
        dialog.show();
    }



    private void navigateToLoginScreen() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}


