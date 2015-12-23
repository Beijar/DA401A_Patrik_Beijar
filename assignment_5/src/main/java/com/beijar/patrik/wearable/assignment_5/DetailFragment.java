package com.beijar.patrik.wearable.assignment_5;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beijar.patrik.wearable.assignment_5.R;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle bundle = getArguments();
        Movie mMovie = (Movie) bundle
                .getSerializable("MOVIE_CHOICE");
        if (mMovie != null){
            ImageView bannerImage = (ImageView)v.findViewById(R.id.banner_image);
            TextView titleText = (TextView)v.findViewById(R.id.movie_title);
            TextView yearText = (TextView)v.findViewById(R.id.movie_year);
            TextView plotText = (TextView)v.findViewById(R.id.movie_plot);

            bannerImage.setImageResource(mMovie.getmFanArt());
            titleText.setText(mMovie.getmTitle());
            yearText.setText(mMovie.getmYear());
            plotText.setText(mMovie.getmInfo());

        } else {
            Log.e("Movie", "Object bundle failed to load");
        }
        return v;
    }


}
