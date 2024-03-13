package com.example.log_in_sign_up;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RoomActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
    }

    public void highlightButton(View view) {
        final Button button = (Button) view;

        // Get the room name from the clicked button
        String roomName = button.getText().toString();

        // Pass the room name to Confirmation activity
        Intent intent = new Intent(RoomActivity.this, Confirmation.class);
        intent.putExtra("roomName", roomName);
        startActivity(intent);

        button.setBackgroundColor(Color.RED);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setBackgroundColor(Color.TRANSPARENT);
            }
        });



    }
}