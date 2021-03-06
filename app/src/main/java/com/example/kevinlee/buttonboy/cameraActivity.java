package com.example.kevinlee.buttonboy;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.view.View;
import android.content.SharedPreferences;
import android.net.Uri;

import java.io.ByteArrayOutputStream;

public class cameraActivity extends AppCompatActivity {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;



    static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();
        setContentView(R.layout.activity_camera);
        dispatchTakePictureIntent();
    }

    private void checkPermissions() {
        int hasCameraPermissions = checkSelfPermission(Manifest.permission.CAMERA);
        if (hasCameraPermissions != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.CAMERA},
                    REQUEST_CODE_ASK_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    return;
                } else {
                    // Permission Denied
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {

//                Bitmap picture = (Bitmap) data.getExtras().get("data");
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//                picture.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] b = baos.toByteArray();
//                String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
//                //Log.i("ITS IN", imageEncoded);
//                SharedPreferences.Editor editor = getSharedPreferences("LOL", MODE_PRIVATE).edit();
//                editor.putString("SENDPHOTO", imageEncoded);
//                editor.apply();

                Intent intent = new Intent(cameraActivity.this,OCRActivity.class);
                intent.putExtra("bitmap", (Bitmap) data.getExtras().get("data"));

                startActivity(intent);
            }
        }
    }


}
