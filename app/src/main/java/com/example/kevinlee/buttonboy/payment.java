package com.example.kevinlee.buttonboy;

import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        final ArrayList<friends> friendsList = (ArrayList<friends>) getIntent().getSerializableExtra("friendsLists");


        friendTotalAdapter adapter = new friendTotalAdapter(friendsList, this);
        final ListView listView = (ListView) findViewById(R.id.totalList);
        listView.setAdapter(adapter);

        Button button;

        button = (Button) findViewById(R.id.request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(payment.this, venmoPay.class);
                FileOutputStream fos;
                try {
                    fos = openFileOutput("friendList", Context.MODE_PRIVATE);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(friendsList);
                    oos.close();
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("Payment","File Error.");
                    startActivity(intent);
                }

            }
        });
    }
}
