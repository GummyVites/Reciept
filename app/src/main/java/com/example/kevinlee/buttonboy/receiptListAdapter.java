package com.example.kevinlee.buttonboy;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class receiptListAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<receipt> list = new ArrayList<receipt>();
    private Context context;




    public receiptListAdapter(ArrayList<receipt> list, Context context) {
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
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        RadioButton checkFriends = (RadioButton) view.findViewById(R.id.checkFriend);
        checkFriends.setTextColor(Color.WHITE);
        checkFriends.setText(list.get(position).name);


        /*TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(list.get(position).name);

        Button splitBtn = (Button)view.findViewById(R.id.splitBtn);

        splitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, splitReceipt.class);
                list.get(position).saveReceipt(context,"receipt");
                context.startActivity(i);
            }
        });
*/
        return view;
    }
}
