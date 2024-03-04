package com.example.log_in_sign_up;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class CropActivity extends AppCompatActivity {

    private static final int CROP_IMAGE_REQUEST = 2;

    private ImageView cropImageView;
    private Button cropButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        cropImageView = findViewById(R.id.cropImageView);
        cropButton = findViewById(R.id.cropButton);

        // Receive the image URI from the MainActivity
        Uri imageUri = getIntent().getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            cropImageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open the oval cropping activity
                startOvalCropActivity(imageUri);
            }
        });
    }

    private void startOvalCropActivity(Uri imageUri) {
        Intent cropIntent = new Intent(this, CropImageActivity.class);
        cropIntent.setData(imageUri);
        startActivityForResult(cropIntent, CROP_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CROP_IMAGE_REQUEST && resultCode == RESULT_OK) {
            // Get the cropped image URI from the oval cropping activity
            Uri ovalCroppedImageUri = data.getData();

            // Pass the oval cropped image back to the MainActivity
            Intent resultIntent = new Intent();
            resultIntent.setData(ovalCroppedImageUri);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }
}
