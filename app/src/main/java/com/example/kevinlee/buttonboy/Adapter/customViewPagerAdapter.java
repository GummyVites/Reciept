package com.example.kevinlee.buttonboy.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.kevinlee.buttonboy.Model.Model;
import com.example.kevinlee.buttonboy.R;
import com.example.kevinlee.buttonboy.TabViewItem;
import com.example.kevinlee.buttonboy.friends;
import com.hold1.pagertabsindicator.TabViewProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 12/5/2017.
 */

public class customViewPagerAdapter extends PagerAdapter implements TabViewProvider.CustomView {
    private ArrayList<friends> list = new ArrayList<friends>();
    List<Model>models;
    Context context;

    public customViewPagerAdapter(List<Model> models, ArrayList<friends> list, Context context) {
        this.list = list;
        this.models = models;
        this.context = context;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "page123" +(position+1);
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object){
        ((ViewGroup)container).removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position){
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.layout_item,container,false);

        TextView textView = (TextView) itemView.findViewById(R.id.txtTextView);
        textView.setText(list.get(position).name+"'s total is"+" "+list.get(position).money.toString());

//        Button venmoUrl = (Button) itemView.findViewById(R.id.payment);
//        venmoUrl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        container.addView(itemView);
        return itemView;
    }

//    @Override
//    public Uri getImageUri(int i) {
//        return null;
//    }
//
//    @Override
//    public int getImageResourceId(int i) {
//        return models.get(i).getId();
//    }

    @Override
    public View getView(int i) {
        return new TabViewItem(context,models.get(i).getTitle(),models.get(i).getId(),0xFF353535,0xFFFF0000);
    }
}
