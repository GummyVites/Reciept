package com.example.kevinlee.buttonboy;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CircleMenu circleMenu = (CircleMenu)findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#FFFFFF"),R.drawable.plus,R.drawable.delete)
                .addSubMenu(Color.parseColor("#FFFFFF"),R.drawable.camera)
                .addSubMenu(Color.parseColor("#FFFFFF"),R.drawable.paypal)
                .addSubMenu(Color.parseColor("#FFFFFF"),R.drawable.receipt)
                .addSubMenu(Color.parseColor("#FFFFFF"),R.drawable.linechart)
                .addSubMenu(Color.parseColor("#FFFFFF"),R.drawable.piechart)
                .addSubMenu(Color.parseColor("#FFFFFF"),R.drawable.screenshot)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index) {
                            // Camera
                            case 0:
                                startActivity(new Intent(MainActivity.this, cameraActivity.class));
                                break;
                            //paypal
                            case 1:
                                break;
                            //receipt
                            case 2:
                                break;
                            //linechart
                            case 3:
                                break;
                            // piechart
                            case 4:
                                break;
                            //screenshot
                            case 5:
                                Toast.makeText(MainActivity.this, "Screen shot clicked", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });

                }


    }
