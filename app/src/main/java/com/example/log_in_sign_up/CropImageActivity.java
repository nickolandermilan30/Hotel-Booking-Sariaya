package com.example.log_in_sign_up;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CropImageActivity extends AppCompatActivity {

    private static final int OVAL_CROP_REQUEST = 3;

    private ImageView ovalCropImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);

        ovalCropImageView = findViewById(R.id.ovalCropImageView);

        ovalCropImageView = findViewById(R.id.ovalCropImageView);

        // Receive the image URI from the CropActivity
        Uri imageUri = getIntent().getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            Bitmap ovalCroppedBitmap = getOvalCroppedBitmap(bitmap);
            ovalCropImageView.setImageBitmap(ovalCroppedBitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Bitmap getOvalCroppedBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap outputBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outputBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        float radius = Math.min(width, height) / 2f;
        canvas.drawCircle(width / 2f, height / 2f, radius, paint);

        return outputBitmap;
    }
}