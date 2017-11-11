package com.example.kevinlee.buttonboy;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OCRActivity extends AppCompatActivity {
    Bitmap image;
    private TessBaseAPI mTess;
    String datapath = "";
    private TessOCR TessOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);

        TessOCR = new TessOCR(OCRActivity.this,"eng");

    }

    public void processImage(View view){
        image = BitmapFactory.decodeResource(getResources(), R.drawable.test);

        String temp = TessOCR.getOCRResult(image);

        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText(temp);
    }
}
