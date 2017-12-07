package com.example.kevinlee.buttonboy;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;



public class OCRActivity extends AppCompatActivity {
    public ImageView mImage;
    public Uri photoURI;
    private TessBaseAPI mTess;
    private TessOCR TessOCR;
    public RingProgressBar mRingProgressBar;
    int progress = 0;

    //Camera stuff
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    private Bitmap bitmap;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.e("IOException", "Error occured while creating the file");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    String TAG = "Error";
    public void grabImage(ImageView imageView)
    {
        this.getContentResolver().notifyChange(photoURI, null);
        ContentResolver cr = this.getContentResolver();
        try
        {
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(cr, photoURI);
            imageView.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Failed to load", e);
        }
    }


    //called after camera intent finished
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if(requestCode==REQUEST_TAKE_PHOTO && resultCode==RESULT_OK)
        {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            this.grabImage(imageView);
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }


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
        dispatchTakePictureIntent();
//        setPicture();
        //Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.receipt1);
        //ImageView iv = (ImageView)findViewById(R.id.imageView);
        //iv.setImageBitmap(image);
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
//    @Override
//    public void onResume(){
//
//    }

    public void processImage(){
        // ONLY UNCOMMENT FOR REAL DEMOS
        /*SharedPreferences editor = getSharedPreferences("LOL", MODE_PRIVATE);
        String photo = editor.getString("SENDPHOTO", "none");
        if(!photo.equals("photo"))
        {
            byte[] b = Base64.decode(photo, Base64.DEFAULT);
            InputStream is = new ByteArrayInputStream(b);
            bitmap = BitmapFactory.decodeStream(is);
        }*/
        //String temp = TessOCR.getOCRResult(bitmap);

//        Bitmap bitmap = getIntent().getParcelableExtra("bitmap");

        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.receipt1);
        String temp = TessOCR.getOCRResult(image);
        textParser parser = new textParser(temp);

        ArrayList<String> itemList = parser.getItemList();
        ArrayList<Float> priceList = parser.getPriceList();

//        Log.i("MEMEEEEE", temp);
//
//        for (String item : itemList){
//            Log.i("ITSME", item);
//        }
//        for(Float item: priceList){
//            Log.i("ITSME", item.toString());
//        }
        TextView tv1 = (TextView)findViewById(R.id.textView);
        tv1.setText(temp);

        receipt receipt = new receipt();
        receipt.setItemNames(itemList);
        receipt.setItemPrices(priceList);
        receipt.saveReceipt(OCRActivity.this,"lists");
    }

//    public void setPicture(){
//        SharedPreferences editor = getSharedPreferences("LOL", MODE_PRIVATE);
//        String photo = editor.getString("SENDPHOTO", "none");
//        if(!photo.equals("photo"))
//        {
//            byte[] b = Base64.decode(photo, Base64.DEFAULT);
//            InputStream is = new ByteArrayInputStream(b);
//            bitmap = BitmapFactory.decodeStream(is);
//            ImageView img = (ImageView) findViewById(R.id.imageView);
//            img.setImageBitmap(bitmap);
//        }
//        ImageView img = (ImageView) findViewById(R.id.imageView);
//        Bitmap bitmaps = getIntent().getParcelableExtra("bitmap");
//        img.setImageBitmap(bitmaps);
//    }

}
