package com.example.kevinlee.buttonboy;

import java.io.Serializable;
import java.util.ArrayList;

public class friends implements Serializable{
    public String name;
    public Float money;
    public ArrayList<item> selectedItems;

    public friends(String name){
        this.name=name;
    }
}
