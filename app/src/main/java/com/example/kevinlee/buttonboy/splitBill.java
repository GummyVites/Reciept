package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class splitBill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_friends_activity);

        ArrayList<receipt> list = new ArrayList<receipt>();
        String[] names = {"Baiwen Huang", "Kevin Lee", "Shyaan Khan", "Chris Evans",
                "Person 1", "Person 2", "Person 3", "Person 4",
                "Person 5", "Person 6", "Person 7"};
        for (int i = 0; i < names.length; ++i){
            receipt temp = new receipt();
            temp.name = names[i];
            temp.itemPrices = new ArrayList<Float>();
            temp.itemNames = new ArrayList<String>();
            for (int j = 0; j < i; ++j) {
                temp.itemNames.add("wall");
                temp.itemPrices.add((float) j);
            }
            list.add(temp);
        }

        //instantiate custom adapter
        receiptListAdapter adapter = new receiptListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listview);
        lView.setAdapter(adapter);

        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), splitReceipt.class);
                //list.get(position).saveReceipt(context,"receipt");
                startActivity(i);
            }
        });
    }

    public void selectedFriend(View view) {

        RadioButton checkFriends = (RadioButton) view.findViewById(R.id.checkFriend);

        Toast toast = Toast.makeText(this, checkFriends.getText() , Toast.LENGTH_SHORT);
        toast.show();
    }
}


