package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.kevinlee.buttonboy.R;
import com.example.kevinlee.buttonboy.item;

import java.util.ArrayList;

public class friendsSelectorAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<friends> list = new ArrayList<friends>();
    private Context context;
    private item item;

    public friendsSelectorAdapter(item item, ArrayList<friends> list, Context context) {
        this.list = list;
        this.context = context;
        this.item = item;
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
            view = inflater.inflate(R.layout.friends_selector, null);
        }

        //Handle TextView and display string from your list
        TextView name = (TextView)view.findViewById(R.id.name);
        name.setText(list.get(position).name);

        CheckBox select = (CheckBox)view.findViewById(R.id.checkBox);
        select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    list.get(position).selectedItems.add(item);
                    item.count += 1;
                } else {
                    list.get(position).selectedItems.remove(item);
                    item.count -= 1;
                }
            }
        });

        return view;
    }
}
