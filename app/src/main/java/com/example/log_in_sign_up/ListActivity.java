package com.example.log_in_sign_up;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ImageButton addTimeButton = findViewById(R.id.addtime);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Magpakita ng AlertDialog at TimePickerDialog
                showTimePickerDialog();
            }
        });




        // Retrieve data from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String hotelName = extras.getString("hotelName");
            String location = extras.getString("location");
            String selectedDate = extras.getString("selectedDate");

            // Display data in TextViews or any other UI components
            TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
            TextView locationTextView = findViewById(R.id.locationTextView);
            TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
            ImageView hotelImageView = findViewById(R.id.hotelImageView);

            hotelNameTextView.setText("Hotel Name: " + hotelName);
            locationTextView.setText("Location: " + location);
            selectedDateTextView.setText("Selected Date: " + selectedDate);

            // Set the hotel image based on hotel name
            if (hotelName.equals("Agathas Hotel powered by Cocotel")) {
                hotelImageView.setImageResource(R.drawable.h1);
            } else if (hotelName.equals("Kehalani Beach Resort by Cocotel")) {
                hotelImageView.setImageResource(R.drawable.h2);
            } else if (hotelName.equals("RedDoorz La Sefa Hotel and Resort Atimonan")) {
                hotelImageView.setImageResource(R.drawable.h3);
            } else if (hotelName.equals("Mango Suites Cauayan")) {
                hotelImageView.setImageResource(R.drawable.h4);
            } else if (hotelName.equals("The Kroun Seafront Residences")) {
                hotelImageView.setImageResource(R.drawable.h5);
            }
        }
    }

    private void showTimePickerDialog() {
        // Current time bilang default values
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // Gumawa ng TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(ListActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // I-set ang napiling oras sa TextView
                        TextView timeTextView = findViewById(R.id.time);
                        timeTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
                    }
                }, hour, minute, false); // false para sa "automatic time" mode

        // Ipakita ang TimePickerDialog
        timePickerDialog.show();
    }

}
