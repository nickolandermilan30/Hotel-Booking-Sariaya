package com.example.log_in_sign_up;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById( R.id.phone);
        final EditText password = findViewById( R.id.password);
        final Button loginBtn = findViewById( R.id.loginBtn);
        final TextView registerNowBtn = findViewById( R.id.registernowBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String phoneTxt =phone.getText().toString();
                final String phoneTxt =phone.getText().toString();
            }
        });
    }
}