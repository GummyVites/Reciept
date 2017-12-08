package com.example.kevinlee.buttonboy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class dataViz extends AppCompatActivity {

    private ArrayList<friends> friendList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_viz);

        PieChart pie = (PieChart) findViewById(R.id.pieChart);
        pie.setTransparentCircleAlpha(0);
        FileInputStream fis;
        try {
            fis = openFileInput("friendList");
            ObjectInputStream ois = new ObjectInputStream(fis);
            friendList = (ArrayList<friends>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            Log.e("Data Viz", "File Error");
        }
        if (friendList != null) {
            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
            for (int i=0; i< friendList.size(); ++i) {
                entries.add(new PieEntry(friendList.get(i).money));
            }
            PieDataSet data = new PieDataSet(entries, "Friends");
            data.setSliceSpace(2);
            data.setValueTextSize(12);
            ArrayList<Integer> colors = new ArrayList<Integer>();
            colors.add(Color.GRAY);
            colors.add(Color.BLUE);
            colors.add(Color.RED);
            colors.add(Color.GREEN);
            colors.add(Color.CYAN);
            colors.add(Color.YELLOW);
            colors.add(Color.MAGENTA);

            data.setColors(colors);

            Legend legend = pie.getLegend();
            legend.setForm(Legend.LegendForm.CIRCLE);
            legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);

            PieData pieData = new PieData(data);
            pie.setData(pieData);
            pie.invalidate();



            pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    int pos = e.toString().indexOf("y: ");
                    String money = e.toString().substring(pos + 3);
                    friends temp = friendList.get(0);
                    for (int i = 0; i<friendList.size(); ++i) {
                        if (friendList.get(i).money == Float.parseFloat(money)) {
                            temp = friendList.get(i);
                            break;
                        }
                    }
                    Toast.makeText(dataViz.this, "Friend " + temp.name + " Paid you: " + temp.money.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected() {

                }
            });
        }

    }
}
