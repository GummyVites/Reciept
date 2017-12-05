package com.example.kevinlee.buttonboy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class cropper extends AppCompatActivity {

    Bitmap bitmap;
     public ImageView resultImageView;
    private Uri mCropImageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cropper);
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                //Bitmap bitmap = result.getBitmap();
                Uri Uri_picture = result.getUri();
                try {
                    InputStream image_stream = getContentResolver().openInputStream(Uri_picture);
                    Bitmap picture = BitmapFactory.decodeStream(image_stream);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] b = baos.toByteArray();
                    String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
                    //Log.i("ITS IN", imageEncoded);
                    SharedPreferences.Editor editor = getSharedPreferences("LOL", MODE_PRIVATE).edit();
                    editor.putString("SENDPHOTO", imageEncoded);
                    editor.apply();
                    startActivity(new Intent(cropper.this, OCRActivity.class));
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
//                Intent intent = new Intent(this,OCRActivity.class);
//                intent.putExtra("bitmap", bitmap);
//                startActivity(new Intent(cropper.this, OCRActivity.class));
                ((ImageView) findViewById(R.id.quick_start_cropped_image)).setImageURI(Uri_picture);
                Toast.makeText(
                        this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG)
                        .show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }





}

