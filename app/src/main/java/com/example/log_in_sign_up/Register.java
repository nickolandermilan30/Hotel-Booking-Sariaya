package com.example.log_in_sign_up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

public class Register extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://loginregister-4f25b-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText Fullname = findViewById(R.id.fullname);
        final EditText Email = findViewById(R.id.email);
        final EditText Phone = findViewById(R.id.phone);
        final EditText Password = findViewById(R.id.password);
        final EditText Conpassword = findViewById(R.id.conpassword);

        final ImageButton registerBtn = findViewById(R.id.registerBtn);
        final ImageButton loginNowBtn = findViewById(R.id.loginNow);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.button_anim);


        final ImageButton showPassButton = findViewById(R.id.showpass);
        final ImageButton showPassButton2 = findViewById(R.id.showpass2);

        showPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(Password, showPassButton);
            }
        });

        showPassButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                togglePasswordVisibility(Conpassword, showPassButton2);
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerBtn.startAnimation(animation);
                final String fullnameTxt = Fullname.getText().toString();
                final String emailTxt = Email.getText().toString();
                final String phoneTxt = Phone.getText().toString();
                final String passwordTxt = Password.getText().toString();
                final String conPasswordTxt = Conpassword.getText().toString();

                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register.this, "Please Fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(Register.this, "Passwrd are not Matching", Toast.LENGTH_SHORT).show();
                }else{

                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)){
                                Toast.makeText(Register.this, "Phone is already Registered", Toast.LENGTH_SHORT).show();
                            }else{
                                databaseReference.child("user").child(phoneTxt).child("Fullname").setValue(fullnameTxt);
                                databaseReference.child("user").child(phoneTxt).child("Email").setValue(emailTxt);
                                databaseReference.child("user").child(phoneTxt).child("Password").setValue(passwordTxt);

                                Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

            }
        });
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginNowBtn.startAnimation(animation);
                finish();
            }
        });



    }  private void togglePasswordVisibility(EditText editText, ImageButton showPassButton) {
        // Toggle password visibility
        int inputType = editText.getInputType();
        if ((inputType & InputType.TYPE_TEXT_VARIATION_PASSWORD) > 0) {
            // Password is currently hidden, show it
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            showPassButton.setImageResource(R.drawable.key_off);
        } else {
            // Password is currently visible, hide it
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            showPassButton.setImageResource(R.drawable.key);
        } editText.setSelection(editText.getText().length());
}}