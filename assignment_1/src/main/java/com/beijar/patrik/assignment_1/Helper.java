package com.beijar.patrik.assignment_1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Helper {

    private String[] ArrayQuotes;

    Helper(String[] quotes){
        ArrayQuotes = quotes;
    }

    public String randomQuote(){
        String todaysQuote;
        int idx = new Random().nextInt(ArrayQuotes.length);
        todaysQuote = (ArrayQuotes[idx]);
        return todaysQuote;
    }

    public String getDate(){
        String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        return date;
    }

}
