package com.example.log_in_sign_up;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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

        RecyclerView recyclerView = findViewById(R.id.recycler);
        ArrayList<String> arrayList = new ArrayList<>();

        //Add multiple images to arraylist.
        arrayList.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0b/0c/4a/0d/main-pool-area-fresh.jpg?w=1200&h=-1&s=1");
        arrayList.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/dc/9f/1d/monte-vista-beach-resort.jpg?w=1200&h=-1&s=1");
        arrayList.add("https://images.summitmedia-digital.com/spotph/images/2023/05/19/the-seves-hotel-resort-sariaya-quezon-6-1684493616.jpg");
        arrayList.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/03/bf/8b/98/balai-sadyaya-resort.jpg?w=1000&h=-1&s=1");
        arrayList.add("https://cf.bstatic.com/xdata/images/hotel/max1024x768/349485169.jpg?k=041463d981428c913a654c98f2cb367051f023c59a895cf1cb9e995a3a60d04d&o=&hp=1");
        arrayList.add("https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/cb/66/14/enjoy-much.jpg?w=700&h=-1&s=1");



        ImageAdapter adapter = new ImageAdapter(MainActivity.this, arrayList);
        recyclerView.setAdapter(adapter);
        ImageButton seeAllTextView = findViewById(R.id.reservenow);

        // Set an OnClickListener for the TextView
        seeAllTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show loading dialog
                showLoadingDialog();

                // Start the new activity after a delay (you can replace this with your actual loading logic)
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss loading dialog
                        dismissLoadingDialog();

                        // Start the new activity when the TextView is clicked
                        Intent intent = new Intent(MainActivity.this, Hotels.class);

                        // Add the user's full name to the Intent
                        intent.putExtra("FullName", fullName);

                        startActivity(intent);
                    }
                }, 2000); // Replace 2000 with the desired delay in milliseconds
            }
        });



        adapter.setOnItemClickListener(new ImageAdapter.OnItemClickListener() {

            @Override
            public void onClick(ImageView imageView, String path) {

            }
        });

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

    private ProgressDialog progressDialog;

    private void showLoadingDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissLoadingDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
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


