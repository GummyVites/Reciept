package com.example.kevinlee.buttonboy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class splitBill extends AppCompatActivity {

    public ArrayList<friends> friendsList;
    public ArrayList<receipt> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_friends_activity);

        friendsList  = new ArrayList<friends>();
        list = new ArrayList<receipt>();
        String[] names = {};
        for (int i = 0; i < names.length; ++i){
            receipt temp = new receipt();
            temp.name = names[i];
            temp.itemPrices = new ArrayList<Float>();
            temp.itemNames = new ArrayList<String>();
            for (int j = 0; j < i; ++j) {
                temp.itemNames.add("wall");
                temp.itemPrices.add((float) j);
            }
            list.add(temp);
        }

        //instantiate custom adapter
        receiptListAdapter adapter = new receiptListAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView) findViewById(R.id.listview);
        lView.setAdapter(adapter);

        Button nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), splitReceipt.class);
                for(int x = 0; x < friendsList.size(); x++)
                {
                    System.out.println(friendsList.get(x));
                }
                if (friendsList.size()!= 0) {
                    i.putExtra("friendsList", friendsList);
                    //list.get(position).saveReceipt(context,"receipt");
                    startActivity(i);
                    finish();
                }else{
                    Toast toast = Toast.makeText(splitBill.this, "Please select friends" , Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        EditText newFriend =  (EditText) findViewById(R.id.friendName);
        newFriend.setOnEditorActionListener(new TextView.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent){
                boolean handled = false;
                if (i== EditorInfo.IME_ACTION_DONE) {
                    String inputText = textView.getText().toString();


                    receipt temp = new receipt();
                    temp.name = inputText;
                    temp.itemPrices = new ArrayList<Float>();
                    temp.itemNames = new ArrayList<String>();
                    for (int j = 0; j < i; ++j) {
                        temp.itemNames.add("wall");
                        temp.itemPrices.add((float) j);
                    }

                    list.add(temp);

                    receiptListAdapter adapter = new receiptListAdapter(list, splitBill.this);

                    //handle listview and assign adapter
                    ListView lView = (ListView) findViewById(R.id.listview);
                    lView.setAdapter(adapter);
                    InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    handled = true;

                }
                return handled;
            }
        });
    }

    public void selectedFriend(View view) {

        CheckBox checkFriends = (CheckBox) view.findViewById(R.id.checkFriend);
        friends temp = new friends(checkFriends.getText().toString());
        temp.money = new Float(0);
        temp.selectedItems = new ArrayList<item>();
        for (int i = 0; i < friendsList.size(); ++i){
            if (friendsList.get(i).name == temp.name) {
                return;
            }
        }
        friendsList.add(temp);
        Toast toast = Toast.makeText(this, checkFriends.getText() +" selected" , Toast.LENGTH_SHORT);
        toast.show();
    }
}


