package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class splitBill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);

        ArrayList<receipt> list = new ArrayList<receipt>();
        for (int i = 1; i <= 10; ++i) {
            receipt temp = new receipt();
            temp.name = "Hodor " + i;
            temp.itemPrices = new ArrayList<Float>();
            temp.itemNames = new ArrayList<String>();
            for (int j=0; j<i; ++j) {
                temp.itemNames.add("wall");
                temp.itemPrices.add((float)j);
            }
            list.add(temp);
        }

        //instantiate custom adapter
        receiptListAdapter adapter = new receiptListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listview);
        lView.setAdapter(adapter);

        Button doneBtn = (Button) findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}


