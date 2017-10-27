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
                            case 0:
                                Toast.makeText(MainActivity.this, "Camera Button Clicked", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, cameraActivity.class));
                                break;
                            case 1:
                                Toast.makeText(MainActivity.this, "Paypal Button Clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(MainActivity.this, "Receipt button Clicked", Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(MainActivity.this, "LineChart Button Clicked ", Toast.LENGTH_SHORT).show();

                                break;
                            case 4:
                                Toast.makeText(MainActivity.this, "Pie Chart Button Clicked", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });

                }


    }
