package com.example.kevinlee.buttonboy;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.InputStream;
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
                progress++;
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
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

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
                        for (int i =0; i<100; i++){
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

    public void processImage(View view){
        SharedPreferences shared = getSharedPreferences("MyApp_Settings", MODE_PRIVATE);
        String photo = shared.getString("PRODUCT_PHOTO", "photo");
        assert photo != null;
        if(!photo.equals("photo"))
        {
            byte[] b = Base64.decode(photo, Base64.DEFAULT);
            InputStream is = new ByteArrayInputStream(b);
            bitmap = BitmapFactory.decodeStream(is);
        }
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(bitmap);
        String temp = TessOCR.getOCRResult(bitmap);
        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText(temp);
    }

}
