package com.example.log_in_sign_up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregister-4f25b-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText phone = findViewById( R.id.phone);
        final EditText password = findViewById( R.id.password);
        final ImageButton loginBtn = findViewById( R.id.loginBtn);
        final ImageButton registerNowBtn = findViewById( R.id.registernowBtn);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_anim);

        final ImageButton showPassButton = findViewById(R.id.showpass);

        showPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toggle password visibility
                int inputType = password.getInputType();
                if ((inputType & InputType.TYPE_TEXT_VARIATION_PASSWORD) > 0) {
                    // Password is currently hidden, show it
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    showPassButton.setImageResource(R.drawable.key_off);
                } else {
                    // Password is currently visible, hide it
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPassButton.setImageResource(R.drawable.key);
                }

                // Move cursor to the end of the text
                password.setSelection(password.getText().length());
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.startAnimation(animation);
                final String phoneTxt = phone.getText().toString();
                final String passwordTxt = password.getText().toString();

                if (phoneTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your mobile or password", Toast.LENGTH_SHORT).show();
                } else {
                    showLoadingProgressDialog();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    dismissLoadingProgressDialog(); // Dismiss loading dialog when data retrieval is complete

                                    if (snapshot.hasChild(phoneTxt)) {
                                        DataSnapshot userSnapshot = snapshot.child(phoneTxt);
                                        if (userSnapshot.hasChild("Password")) {
                                            final String getPassword = userSnapshot.child("Password").getValue(String.class);
                                            if (getPassword != null && getPassword.equals(passwordTxt)) {
                                                // Successfully logged in
                                                Toast.makeText(Login.this, "Successfully logged in", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Login.this, MainActivity.class));
                                                finish();
                                            } else {
                                                // Wrong Password
                                                Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // "Password" child not found
                                            Toast.makeText(Login.this, "Password not found for the user", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        // User not found
                                        Toast.makeText(Login.this, "User not found", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    dismissLoadingProgressDialog(); // Dismiss loading dialog on onCancelled
                                }
                            });
                        }
                    }, 1000); // Delay for 1000 milliseconds (1 second), you can adjust this time as needed
                }
            }
        });


        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNowBtn.startAnimation(animation);

                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
    private ProgressDialog progressDialog;
    private void showLoadingProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissLoadingProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}