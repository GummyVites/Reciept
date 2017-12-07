package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


//
//        acceptBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO send money req
//                for (int i=0; i < friendsList.size(); ++i) {
//                    friends temp = friendsList.get(i);
//                    for (int j=0; j < temp.selectedItems.size(); ++j) {
//                        temp.money += temp.selectedItems.get(j).cost / (temp.selectedItems.get(j).count);
//                    }
//                }
////                Toast toast = Toast.makeText(v.getContext(), friendsList.get(0).money.toString() , Toast.LENGTH_SHORT);
////                toast.show();
//                Intent i = new Intent(splitReceipt.this,tabbedFriends.class);
//                i.putExtra("friendsLists", friendsList);
//                startActivity(i);
//                finish();
//            }
//        });
//
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO cancel money req
//                finish();
//            }
//        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.action_add:
                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(splitReceipt.this);
                        View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                        final EditText itemName = (EditText) mView.findViewById(R.id.addName);
                        final EditText itemPrice = (EditText) mView.findViewById(R.id.addPrice);
                        Button mLogin = (Button) mView.findViewById(R.id.addItemButton);
                        mBuilder.setView(mView);
                        final AlertDialog dialog = mBuilder.create();
                        dialog.show();
                        mLogin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!itemName.getText().toString().isEmpty() && !itemPrice.getText().toString().isEmpty()){
                                    String newItem = itemName.getText().toString();
                                    String newPrice = itemPrice.getText().toString();
                                    item temp = new item();
                                    temp.name=newItem;
                                    temp.cost=Float.valueOf(newPrice);
                                    items.add(temp);
                                    Toast.makeText(splitReceipt.this,
                                            R.string.success_msg,
                                            Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(splitReceipt.this,
                                            R.string.error_msg,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        break;

                    case R.id.action_remove:
                        Toast.makeText(splitReceipt.this,"Remove add Clicked",Toast.LENGTH_SHORT);
                        break;

                    case R.id.action_next:
                        //TODO send money req
                        for (int i=0; i < friendsList.size(); ++i) {
                            friends temp = friendsList.get(i);
                            for (int j=0; j < temp.selectedItems.size(); ++j) {
                                temp.money += temp.selectedItems.get(j).cost / (temp.selectedItems.get(j).count);
                            }
                        }
                        Intent i = new Intent(splitReceipt.this,tabbedFriends.class);
                        i.putExtra("friendsLists", friendsList);
                        startActivity(i);
                        finish();
                        break;
                }
            return true;
            }
        });
    }
}
