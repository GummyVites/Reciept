package com.example.kevinlee.buttonboy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ArrayList<friends> friendsList = (ArrayList<friends>) getIntent().getSerializableExtra("friendsLists");


        friendTotalAdapter adapter = new friendTotalAdapter(friendsList, this);
        final ListView listView = (ListView) findViewById(R.id.totalList);
        listView.setAdapter(adapter);

        Button button;

        button = (Button) findViewById(R.id.request);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}
