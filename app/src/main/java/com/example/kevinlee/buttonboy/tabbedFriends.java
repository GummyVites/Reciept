package com.example.kevinlee.buttonboy;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.kevinlee.buttonboy.Adapter.customViewPagerAdapter;
import com.example.kevinlee.buttonboy.Model.Model;
import com.hold1.pagertabsindicator.PagerTabsIndicator;

import java.util.ArrayList;
import java.util.List;

public class tabbedFriends extends AppCompatActivity {
    List<Model> models = new ArrayList<>();

    ViewPager viewPager;
    PagerTabsIndicator tabsIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_friends);
        
        initModel();

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        tabsIndicator = (PagerTabsIndicator)findViewById(R.id.tabs_indicator);
        viewPager.setAdapter(new customViewPagerAdapter(models,this));
        tabsIndicator.setViewPager(viewPager);
    }

    private void initModel() {
//        for (int i = 0; i < models.size();i++){
//            Model model = new Model("page"+i,R.drawable.ic_person_black_24dp);
//            models.add(model);
//        }
        Model model = new Model("page 11",R.drawable.ic_person_outline_black_24dp);
        models.add(model);
        model = new Model("page 21",R.drawable.ic_person_black_24dp);
        models.add(model);
        model = new Model("page 31",R.drawable.ic_person_black_24dp);
        models.add(model);
        model = new Model("page 41",R.drawable.ic_person_black_24dp);
        models.add(model);
        model = new Model("page 51",R.drawable.ic_person_black_24dp);
        models.add(model);

    }
}
