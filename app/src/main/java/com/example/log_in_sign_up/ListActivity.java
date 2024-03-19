package com.example.log_in_sign_up;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class ListActivity extends AppCompatActivity {

    private CountDownTimer countDownTimer;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Display saved data if available
        displaySavedData();

        ImageButton addTimeButton = findViewById(R.id.addtime);
        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Magpakita ng AlertDialog at TimePickerDialog
                showTimePickerDialog();
            }
        });
        ImageButton homeButton = findViewById(R.id.b1);
        ImageButton roomButton = findViewById(R.id.b3);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pumunta sa Room activity kapag pindutin ang button na "Room"
                Intent intent = new Intent(ListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pumunta sa Room activity kapag pindutin ang button na "Room"
                Intent intent = new Intent(ListActivity.this, RoomActivity.class);
                startActivity(intent);
            }
        });

        ImageButton deltimeButton = findViewById(R.id.deltime);
        deltimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dito mo ilalagay ang logic para tanggalin ang oras na sinet
                TextView timeTextView = findViewById(R.id.time);
                timeTextView.setText("Time: ");
                // I-cancel ang nauna pang countdown timer kung mayroon man
                cancelCountdownTimer();
            }
        });

        ImageButton playButton = findViewById(R.id.play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kumuha ng oras na nakaset mula sa timeTextView
                TextView timeTextView = findViewById(R.id.time);
                String timeString = timeTextView.getText().toString();

                // Split ang oras, minuto, at AM/PM
                String[] parts = timeString.split(":");
                String[] amPmParts = parts[1].split(" ");
                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(amPmParts[0]);
                boolean isPm = amPmParts[1].equals("PM");

                // Kung ang AM/PM ay PM at ang oras ay hindi 12, i-add 12 sa oras
                if (isPm && hour != 12) {
                    hour += 12;
                }

                // Kumunwari na may oras na itinakda
                if (!timeString.equals("Time: ")) {
                    // Gumawa ng CountDownTimer
                    long totalMilliseconds = ((hour % 12) * 3600 + minute * 60) * 1000; // Convert oras at minuto sa milliseconds
                    startCountdownTimer(totalMilliseconds);
                    // Itago ang playButton pagkatapos mong pindutin ito
                    playButton.setVisibility(View.GONE);
                } else {
                    // Kung walang itinakdang oras, mag-anunsyo na walang itinakdang oras
                    Toast.makeText(ListActivity.this, "Walang itinakdang oras!", Toast.LENGTH_SHORT).show();
                }
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

            hotelNameTextView.setText(hotelName);
            locationTextView.setText(location);
            selectedDateTextView.setText(selectedDate);

            // Set the hotel image based on hotel name
            switch (hotelName) {
                case "Agathas Hotel powered by Cocotel":
                    hotelImageView.setImageResource(R.drawable.h1);
                    break;
                case "Kehalani Beach Resort by Cocotel":
                    hotelImageView.setImageResource(R.drawable.h2);
                    break;
                case "RedDoorz La Sefa Hotel and Resort Atimonan":
                    hotelImageView.setImageResource(R.drawable.h3);
                    break;
                case "Mango Suites Cauayan":
                    hotelImageView.setImageResource(R.drawable.h4);
                    break;
                case "The Kroun Seafront Residences":
                    hotelImageView.setImageResource(R.drawable.h5);
                    break;
            }
        }
    }

    private void displaySavedData() {
        // Retrieve saved data from SharedPreferences
        String hotelName = sharedPreferences.getString("hotelName", "");
        String location = sharedPreferences.getString("location", "");
        String selectedDate = sharedPreferences.getString("selectedDate", "");
        String hotelImage = sharedPreferences.getString("hotelImage", ""); // Retrieve hotel image name

        // Display saved data in TextViews and ImageView
        TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
        ImageView hotelImageView = findViewById(R.id.hotelImageView);

        hotelNameTextView.setText(hotelName);
        locationTextView.setText(location);
        selectedDateTextView.setText(selectedDate);

        // Set the hotel image based on hotel image name
        switch (hotelImage) {
            case "h1":
                hotelImageView.setImageResource(R.drawable.h1);
                break;
            case "h2":
                hotelImageView.setImageResource(R.drawable.h2);
                break;
            case "h3":
                hotelImageView.setImageResource(R.drawable.h3);
                break;
            case "h4":
                hotelImageView.setImageResource(R.drawable.h4);
                break;
            case "h5":
                hotelImageView.setImageResource(R.drawable.h5);
                break;

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Save data from TextViews and ImageView to SharedPreferences
        TextView hotelNameTextView = findViewById(R.id.hotelNameTextView);
        TextView locationTextView = findViewById(R.id.locationTextView);
        TextView selectedDateTextView = findViewById(R.id.selectedDateTextView);
        ImageView hotelImageView = findViewById(R.id.hotelImageView);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("hotelName", hotelNameTextView.getText().toString());
        editor.putString("location", locationTextView.getText().toString());
        editor.putString("selectedDate", selectedDateTextView.getText().toString());

        // Get the hotel image name from the resource id
        String hotelImageName = getHotelImageName(hotelImageView.getDrawable().getConstantState());
        editor.putString("hotelImage", hotelImageName);
        editor.apply();
    }

    // Helper method to get the hotel image name from the resource id
    private String getHotelImageName(Drawable.ConstantState constantState) {
        if (constantState.equals(getResources().getDrawable(R.drawable.h1).getConstantState())) {
            return "h1";
        } else if (constantState.equals(getResources().getDrawable(R.drawable.h2).getConstantState())) {
            return "h2";
        } else if (constantState.equals(getResources().getDrawable(R.drawable.h3).getConstantState())) {
            return "h3";
        } else if (constantState.equals(getResources().getDrawable(R.drawable.h4).getConstantState())) {
            return "h4";
        } else if (constantState.equals(getResources().getDrawable(R.drawable.h5).getConstantState())) {
            return "h5";
        } else {
            // Return default image name if none of the above matches
            return "default_image";
        }
    }


    private void showTimePickerDialog() {
        // Default values ng oras
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int amPmValue = cal.get(Calendar.AM_PM);

        // Gumawa ng AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
        builder.setTitle("Set Time");

        // Gumawa ng View para sa AlertDialog
        View view = getLayoutInflater().inflate(R.layout.time_picker_dialog, null);

        // Initialize ng NumberPicker para sa oras
        NumberPicker hourPicker = view.findViewById(R.id.hourPicker);
        hourPicker.setMinValue(0);
        hourPicker.setMaxValue(23); // 24-hour format
        hourPicker.setValue(hour);

        // Initialize ng NumberPicker para sa minuto
        NumberPicker minutePicker = view.findViewById(R.id.minutePicker);
        minutePicker.setMinValue(0);
        minutePicker.setMaxValue(59);
        minutePicker.setValue(minute);

        // Initialize ng NumberPicker para sa AM/PM
        NumberPicker amPmPicker = view.findViewById(R.id.amPmPicker);
        amPmPicker.setMinValue(0);
        amPmPicker.setMaxValue(1); // 0: AM, 1: PM
        amPmPicker.setDisplayedValues(new String[]{"AM", "PM"}); // I-display ang mga values
        amPmPicker.setValue(amPmValue);

        // I-set ang layout sa AlertDialog
        builder.setView(view);

        // I-set ang positive button at ang action nito
        builder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Kumuha ng oras, minuto, at AM/PM mula sa NumberPicker
                int selectedHour = hourPicker.getValue();
                int selectedMinute = minutePicker.getValue();
                String selectedAmPm = amPmPicker.getValue() == 0 ? "AM" : "PM";

                // I-set ang napiling oras sa TextView
                TextView timeTextView = findViewById(R.id.time);
                timeTextView.setText(String.format(Locale.getDefault(), "%02d:%02d %s", selectedHour, selectedMinute, selectedAmPm));
            }
        });

        // I-set ang negative button at ang action nito
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Walang gagawin kung i-cancel ng user ang pag-set ng oras
            }
        });

        // Ipakita ang AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    // Simulan ang countdown timer
    private void startCountdownTimer(long milliseconds) {
        // I-cancel ang nauna pang countdown timer kung mayroon man
        cancelCountdownTimer();

        countDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update ang UI (kung kinakailangan) sa bawat tick ng timer
                // Halimbawa: ipakita ang natitirang oras sa isang TextView
                long secondsUntilFinished = millisUntilFinished / 1000;
                long minutes = secondsUntilFinished / 60;
                long seconds = secondsUntilFinished % 60;

                // Ipakita ang natitirang oras sa isang TextView
                TextView countdownTextView = findViewById(R.id.time);
                countdownTextView.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
            }
            // I-update ang onFinish method sa CountDownTimer
            @Override
            public void onFinish() {
                // Ipakita ang custom alert dialog kapag natapos na ang timer
                showCustomAlertDialog();
            }

            // Lumikha ng method para ipakita ang custom alert dialog
            private void showCustomAlertDialog() {
                // Gumawa ng AlertDialog.Builder
                AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog_layout, null);

                // Initialize ng mga views sa custom dialog layout
                TextView dialogTitle = dialogView.findViewById(R.id.dialog_title);
                TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
                Button okButton = dialogView.findViewById(R.id.ok_button);

                // Set ng mga teksto sa custom dialog
                dialogTitle.setText("Timer Alert");
                dialogMessage.setText("Your time is up!");

                // I-set ang custom layout sa AlertDialog
                builder.setView(dialogView);

                // I-set ang positive button sa AlertDialog
                builder.setPositiveButton(null, null);

                // Ipakita ang AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                // I-set ang onClickListener para sa OK button
                okButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });
            }


        }.start();
    }

    private void cancelCountdownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Siguraduhing i-cancel ang countdown timer sa pag-destroy ng activity
        cancelCountdownTimer();
    }
}
