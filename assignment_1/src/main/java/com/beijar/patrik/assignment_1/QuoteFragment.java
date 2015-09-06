package com.beijar.patrik.assignment_1;


import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    public QuoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        TextView text = (TextView)view.findViewById(R.id.quote_text);
        TextView todaysDate = (TextView)view.findViewById(R.id.todays_date);
        text.setText(randomQuote());
        todaysDate.setText(getDate());

        return view;
    }

    public String randomQuote(){
        String quote;
        Resources res = getResources();
        String[] quotes = res.getStringArray(R.array.quotes);
        int idx = new Random().nextInt(quotes.length);
        quote = (quotes[idx]);

        return quote;
    }

    public String getDate(){
        String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        return date;
    }

}
