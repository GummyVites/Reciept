package com.example.kevinlee.buttonboy;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
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

public class recieptItemAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<item> list = new ArrayList<item>();
    private Context context;



    public recieptItemAdapter(ArrayList<item> list, Context context) {
        this.list = list;
        this.context = context;
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
        ArrayList<friends> myFriends = new ArrayList<friends>();
        friends Baiwen = new friends("Baiwen");
        friends Kevin = new friends("Kevin");
        friends Shyaan = new friends("Shyaan");
        friends Chris = new friends("Chris");
        myFriends.add(Baiwen);
        myFriends.add(Kevin);
        myFriends.add(Shyaan);
        myFriends.add(Chris);


        friendsSelectorAdapter friendsSelectorAdapter = new friendsSelectorAdapter(myFriends, context);

        ListView friendsList = (ListView)view.findViewById(R.id.friend_selector);
        friendsList.setAdapter(friendsSelectorAdapter);

        //Handle TextView and display string from your list
        TextView itemCost = (TextView)view.findViewById(R.id.item_cost);
        itemCost.setText("$ " + list.get(position).cost.toString());

        TextView itemName = (TextView) view.findViewById(R.id.item_name);
        itemName.setText(list.get(position).name);


        return view;
    }
}