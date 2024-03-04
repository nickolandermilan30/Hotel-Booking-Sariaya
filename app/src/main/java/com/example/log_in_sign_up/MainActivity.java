package com.example.log_in_sign_up;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

        // Hanapin ang addimg button at i-set ang OnClickListener
        ImageButton addImageButton = findViewById(R.id.addimg);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go to the gallery
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery, PICK_IMAGE);
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

            // Load the image URL from SharedPreferences and display using Glide
            loadAndDisplayImage();
        }
    }

    private void loadAndDisplayImage() {
        // Load the image URL from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        String imageUrl = preferences.getString(phoneNumber + "_imageUrl", null);

        if (imageUrl != null) {
            // Display the image using Glide
            ImageView imageView = findViewById(R.id.imageshow);
            Glide.with(this).load(imageUrl).into(imageView);
        }
    }

    // Kapag nakuha na ang resulta mula sa gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                // Get the image from URI
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                // Crop the image to an oval
                Bitmap croppedBitmap = cropToOval(bitmap);

                // Set the oval image to imageshow
                ImageView imageView = findViewById(R.id.imageshow);
                imageView.setImageBitmap(croppedBitmap);

                // Upload the image to Firebase Storage
                String storagePath = "images/" + phoneNumber + "_profile.jpg";
                uploadImageToFirebaseStorage(bitmap, storagePath);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadImageToFirebaseStorage(Bitmap bitmap, String storagePath) {
        // Upload the image to Firebase Storage
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(storagePath);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = storageRef.putBytes(data);

        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Get the image URL after successful upload
            storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                String imageUrl = uri.toString();
                // Save the image URL to Firebase Realtime Database
                saveImageUrlToDatabase(imageUrl);
            });
        });
    }

    private void saveImageUrlToDatabase(String imageUrl) {
        String phoneNumber = this.phoneNumber;

        // Save the image URL to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("UserProfile", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(phoneNumber + "_imageUrl", imageUrl);
        editor.apply();

        // Save the image URL to Firebase Realtime Database
        DatabaseReference userRef = databaseReference.child("user").child(phoneNumber);
        userRef.child("ImageUrl").setValue(imageUrl);

        // Load the image using Glide
        ImageView imageView = findViewById(R.id.imageshow);
        Glide.with(this).load(imageUrl).into(imageView);
    }




    // Method para sa pag-crop ng larawan sa isang oval
    private Bitmap cropToOval(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(outputBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        RectF rect = new RectF(0, 0, width, height);

        canvas.drawOval(rect, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0, 0, paint);

        return outputBitmap;
    }
}
