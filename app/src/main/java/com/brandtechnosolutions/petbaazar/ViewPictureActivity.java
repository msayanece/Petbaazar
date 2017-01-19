package com.brandtechnosolutions.petbaazar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

public class ViewPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "on next activity created", Toast.LENGTH_LONG).show();
        setContentView(R.layout.activity_view_picture);
        Intent intent = getIntent();
        Bitmap bitmapImage = (Bitmap) intent.getParcelableExtra("bmp_Image");
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view_id);
        imageView.setImageBitmap(bitmapImage);
//        Toast.makeText(this,"on next activity created",Toast.LENGTH_LONG).show();
    }
}
