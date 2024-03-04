package com.example.log_in_sign_up;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView imageShow;
    private ImageButton addImg;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int OVAL_CROP_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageShow = findViewById(R.id.imageshow);
        addImg = findViewById(R.id.addimg);

        addImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        // Retrieve user information from Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String fullName = extras.getString("FullName");
            String phoneNumber = extras.getString("PhoneNumber");

            // Set user information in TextViews
            TextView fullNameTextView = findViewById(R.id.fullNameTextView);
            TextView phoneNumberTextView = findViewById(R.id.phoneNumberTextView);

            fullNameTextView.setText(fullName);
            phoneNumberTextView.setText(phoneNumber);
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();

            // Start the CropActivity and pass the selected image URI
            startCropActivity(imageUri);
        } else if (requestCode == OVAL_CROP_REQUEST && resultCode == RESULT_OK) {
            // Get the oval cropped image URI from CropImageActivity
            Uri ovalCroppedImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ovalCroppedImageUri);
                imageShow.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void startCropActivity(Uri imageUri) {
        Intent cropIntent = new Intent(this, CropActivity.class);
        cropIntent.setData(imageUri);
        startActivityForResult(cropIntent, OVAL_CROP_REQUEST);
    }}
