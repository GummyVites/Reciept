package com.example.kevinlee.buttonboy;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;



public class OCRActivity extends AppCompatActivity {
    public ImageView mImage;
    public Uri mImageUri;
    private TessBaseAPI mTess;
    private TessOCR TessOCR;
    public  Bitmap bitmap;
    public RingProgressBar mRingProgressBar;
    int progress = 0;

    Handler myHandler = new Handler()
    {
        @Override
        public void handleMessage (Message msg){
        if (msg.what == 0) {
            if (progress < 100) {
                progress+=20;
                mRingProgressBar.setProgress(progress);
            }
        }
    }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocr);
        TessOCR = new TessOCR(OCRActivity.this,"eng");
        //setPicture();
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.receipt2);
        ImageView iv = (ImageView)findViewById(R.id.imageView);
        iv.setImageBitmap(image);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                processImage();
                mRingProgressBar = (RingProgressBar) findViewById(R.id.progress_bar_2);
                mRingProgressBar.setProgress(progress);
                mRingProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener(){
                    @Override
                    public void progressToComplete()
                    {
                        // Progress reaches the maximum callback default Max value is 100
                        Toast.makeText(OCRActivity.this, "complete", Toast.LENGTH_SHORT).show();
                    }
                });

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i =0; i<100; i+=20){
                            try{
                                Thread.sleep(100);
                                myHandler.sendEmptyMessage(0);
                            }catch(InterruptedException e){
                                e.printStackTrace();
                            }
                        }

                    }
                }).start();

            }
        });
    }

    public void processImage(){
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.receipt2);
        String temp = TessOCR.getOCRResult(image);
        textParser parser = new textParser(temp);
        Log.i("MEMEEEEE", temp);
        ArrayList<String> itemList = parser.getItemList();
        ArrayList<Float> priceList = parser.getPriceList();
        for (String item : itemList){
            Log.i("ITSME", item);
        }
        for(Float item: priceList){
            Log.i("ITSME", item.toString());
        }
        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText(temp);

        receipt receipt = new receipt();
        receipt.setItemNames(itemList);
        receipt.setItemPrices(priceList);
        receipt.saveReceipt(OCRActivity.this,"lists");
    }

    public void setPicture(){
        SharedPreferences editor = getSharedPreferences("LOL", MODE_PRIVATE);
        String photo = editor.getString("SENDPHOTO", "none");
        if(!photo.equals("photo"))
        {
            byte[] b = Base64.decode(photo, Base64.DEFAULT);
            InputStream is = new ByteArrayInputStream(b);
            bitmap = BitmapFactory.decodeStream(is);
            //ImageView img = (ImageView) findViewById(R.id.imageView4);
            //img.setImageBitmap(bitmap);
        }
    }

}
