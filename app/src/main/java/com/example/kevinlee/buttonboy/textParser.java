package com.example.kevinlee.buttonboy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Kraken on 11/13/17.
 */

public class textParser {

    ArrayList<String[]> itemList = new ArrayList<>();

    //Splits text by newline and then space
    public textParser(String text){
        String lines[] = text.split("\\r?\\n");
        for (int line=0; line<lines.length; line++) {
            itemList.add(lines[line].split("\\s+"));
        }
    }
    // Each outer loop is a different line
    // Each inner loop is a different word
    public ArrayList<String[]> getList(){
        return itemList;
    }

    public String getItem(int sentIndex, int wordIndex){
        return itemList.get(sentIndex)[wordIndex];
    }
}
