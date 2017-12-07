package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class splitReceipt extends AppCompatActivity   {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_receipt);
        receipt tempReceipt = new receipt();
        tempReceipt = tempReceipt.load(splitReceipt.this,"lists");
        final ArrayList<friends> friendsList = (ArrayList<friends>) getIntent().getSerializableExtra("friendsList");

        final ArrayList<item> items= new ArrayList<item>();
        for (int i =0; i<tempReceipt.itemPrices.size(); ++i) {
            item temp = new item();
            temp.name=tempReceipt.itemNames.get(i);
            temp.cost=tempReceipt.itemPrices.get(i);
            items.add(temp);
        }

        final recieptItemAdapter itemAdapter = new recieptItemAdapter(items, friendsList, this);
        final ListView listView = (ListView) findViewById(R.id.receiptItemList);
        listView.setAdapter(itemAdapter);

        Button acceptBtn = (Button) findViewById(R.id.acceptBtn);
        Button cancelBtn = (Button) findViewById(R.id.cancelBtn);

        acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO send money req
                for (int i=0; i < friendsList.size(); ++i) {
                    friends temp = friendsList.get(i);
                    for (int j=0; j < temp.selectedItems.size(); ++j) {
                        temp.money += temp.selectedItems.get(j).cost / (temp.selectedItems.get(j).count);
                        temp.selectedItems.get(j).count=temp.selectedItems.get(j).count + 1;
                    }
                }
//                Toast toast = Toast.makeText(v.getContext(), friendsList.get(0).money.toString() , Toast.LENGTH_SHORT);
//                toast.show();
                Intent i = new Intent(splitReceipt.this,tabbedFriends.class);
                i.putExtra("friendsLists", friendsList);
                startActivity(i);
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
