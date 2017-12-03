package com.example.kevinlee.buttonboy;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

public class recieptItemAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<item> list = new ArrayList<item>();
    private Context context;
    private ArrayList<friends> friendsList = new ArrayList<friends>();

    public recieptItemAdapter(ArrayList<item> list, ArrayList<friends> friendsList, Context context) {
        this.list = list;
        this.context = context;
        this.friendsList = friendsList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.receipt_item, null);
        }

        /*ArrayList<friends> myFriends = new ArrayList<friends>();
        friends Baiwen = new friends("Baiwen");
        friends Kevin = new friends("Kevin");
        friends Shyaan = new friends("Shyaan");
        friends Chris = new friends("Chris");
        myFriends.add(Baiwen);
        myFriends.add(Kevin);
        myFriends.add(Shyaan);
        myFriends.add(Chris);*/


        friendsSelectorAdapter friendsSelectorAdapter = new friendsSelectorAdapter(list.get(position),friendsList, context);

        ListView friendsList = (ListView)view.findViewById(R.id.friend_selector);
        friendsList.setAdapter(friendsSelectorAdapter);

        //Handle TextView and display string from your list
        TextView itemCost = (TextView)view.findViewById(R.id.item_cost);
        itemCost.setTextColor(Color.WHITE);
        itemCost.setText("$" + list.get(position).cost.toString());

        final TextView itemName = (TextView) view.findViewById(R.id.item_name);
        itemName.setTextColor(Color.WHITE);
        itemName.setText(list.get(position).name);

        itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
                final LayoutInflater inflater = LayoutInflater.from(context);
                final View vw = inflater.inflate(R.layout.change_item, null);
                final EditText newItemName = (EditText) vw.findViewById(R.id.newItemName);
                newItemName.setText(itemName.getText().toString());


                mBuilder.setView(vw).setTitle("Enter New Item Name").setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        View vw2 = inflater.inflate(R.layout.activity_split_receipt, null);
                        //ListView receiptItemList = (ListView) vw2.findViewById(R.id.receiptItemList);

                        for(int i = 0; i < list.size();i++) {
                                if(itemName.getText().toString().equals(list.get(i).name))
                                {
                                    String newItem = newItemName.getText().toString();
                                    itemName.setText(newItem);
                                    item it = new item();
                                    it.name = newItem;
                                    list.set(i, it);
                                }
                        }

                        //Toast toast = Toast.makeText(vw.getContext(), , Toast.LENGTH_SHORT);
                        //toast.show();
                    }
                });
                AlertDialog dialog = mBuilder.create();
                dialog.show();
            }
        });


        return view;
    }
}