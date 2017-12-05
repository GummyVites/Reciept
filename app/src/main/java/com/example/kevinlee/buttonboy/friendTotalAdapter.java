package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;


import java.util.ArrayList;




public class friendTotalAdapter extends BaseAdapter implements ListAdapter  {
    private ArrayList<friends> list = new ArrayList<friends>();
    private Context context;
//    private Float money;
//    private String name;


    public  friendTotalAdapter(ArrayList<friends> list, Context context) {
        this.list = list;
        this.context = context;
//        this.money = money;
//        this.name = name;
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
            view = inflater.inflate(R.layout.payment_name_money, null);
        }

        //Handle TextView and display string from your list
        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(list.get(position).name);

        TextView money = (TextView)view.findViewById(R.id.total);
        money.setText(list.get(position).money.toString());

        return view;
    }
}
