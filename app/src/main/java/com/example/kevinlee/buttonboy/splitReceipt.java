package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class splitReceipt extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_receipt);
        receipt tempReceipt = new receipt();
        tempReceipt = tempReceipt.load(splitReceipt.this);

        ArrayList<item> items= new ArrayList<item>();
        for (int i =0; i<tempReceipt.itemPrices.size(); ++i) {
            item temp = new item();
            temp.name=tempReceipt.itemNames.get(i);
            temp.cost=tempReceipt.itemPrices.get(i);
            items.add(temp);
        }

        recieptItemAdapter itemAdapter = new recieptItemAdapter(items, this);
        ListView listView = (ListView) findViewById(R.id.receiptItemList);
        listView.setAdapter(itemAdapter);

        Button acceptBtn = (Button) findViewById(R.id.acceptBtn);
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send money req
                finish();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO cancel money req
                finish();
            }
        });
    }
}
