package com.example.kevinlee.buttonboy;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class textParser {

    private ArrayList<String[]> list = new ArrayList<>();
    private ArrayList<String> itemList = new ArrayList<>();
    private ArrayList<Float> priceList = new ArrayList<>();

    private final String priceRegex = "(.+)(\\W+)(\\d+[,.]\\d\\d)";
//    final String subtotalRegex="^([Ss][Uu][Bb][Tt][Oo][Tt][Aa][Ll]) +(\\s) +(\\d+([,.][0-9][0-9]))$";
//    final String taxRegex="^([Tt][Aa][Xx][Ee]?[Ss]?)+(\\s)+(\\d+([,.][0-9][0-9]))$";
//    final String totalRegex="^([Tt][Oo][Tt][Aa][Ll])+(\\s)+(\\d+([,.][0-9][0-9]))$";


    //Splits text by newline and then space
    public textParser(String text){
//        String lines[] = text.split("\\r?\\n");
//        for (int line=0; line<lines.length; line++) {
//            itemList.add(lines[line].split("\\s+"));
//        }

        final Pattern pattern = Pattern.compile(priceRegex);
        final Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            itemList.add(matcher.group(1));
            priceList.add(Float.valueOf(matcher.group(3)));
        }
    }
    // Each outer loop is a different line
    // Each inner loop is a different word
    public String getItem(int sentIndex, int wordIndex){
        return list.get(sentIndex)[wordIndex];
    }

    public ArrayList<String> getItemList(){
        return itemList;
    }
    public ArrayList<Float> getPriceList(){
        return  priceList;
    }





}
