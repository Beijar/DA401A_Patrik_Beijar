package com.beijar.patrik.assignment_1;


import android.app.FragmentTransaction;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonFragment extends Fragment implements View.OnClickListener{

    public ButtonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment with a button click listener
        View v = inflater.inflate(R.layout.fragment_button, container, false);
        View button_view = v.findViewById(R.id.button);
        button_view.setOnClickListener(this);
        return v;
    }

    public void onClick(View v){
        //TODO Look up pros and cons of onClick in java vs xml
        // Create new fragment and transaction
        QuoteFragment newFragment = new QuoteFragment();
        android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }


}
