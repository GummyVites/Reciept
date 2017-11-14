package com.example.kevinlee.buttonboy;

import android.app.Activity;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Intent;
import android.util.Base64;
import android.widget.ImageView;
import android.view.View;
import android.content.SharedPreferences;
import android.net.Uri;

import java.io.ByteArrayOutputStream;

public class cameraActivity extends AppCompatActivity {

    public ImageView mImage;
    public Uri mImageUri;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
        this.imageview = (ImageView)this.findViewById(R.id.imageView1);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {

                Bitmap picture = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] b = baos.toByteArray();
                String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
                SharedPreferences shared = getSharedPreferences("MyApp_settings", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("PRODUCT_PHOTO", imageEncoded);
                editor.commit();


                // Sets the ImageView with the Image URI
                startActivity(new Intent(cameraActivity.this, OCRActivity.class));
            }
        }
    }


}
