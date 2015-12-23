package com.beijar.patrik.wearable.assignment_5;



import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MasterFragment extends Fragment {
    public static final String FRAGMENT_BUNDLE_KEY = "MOVIE_CHOICE";
    List<Movie> mMovieList = new ArrayList<Movie>();


    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(R.array.movies);
        int n = ta.length();

        for (int i = 0; i < n; i++) {
            TypedArray movieData = res.obtainTypedArray(ta.getResourceId(i, 0));
            Movie movie = new Movie(movieData.getString(0), movieData.getString(1), movieData.getString(2), movieData.getResourceId(4, 0), movieData.getResourceId(3, 0));
            mMovieList.add(movie);
            movieData.recycle();
        }

        ta.recycle();

        View v = inflater.inflate(R.layout.fragment_master, container, false);
        GridView gridview = (GridView) v.findViewById(R.id.gridview);
        gridview.setAdapter(new GridAdapter(v.getContext(), mMovieList));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                // Create new fragment and bundle
                DetailFragment newFragment = new DetailFragment();
                Bundle bundle = new Bundle();
                Movie movie = mMovieList.get(position);
                if (movie != null) {
                    bundle.putSerializable(FRAGMENT_BUNDLE_KEY, movie);
                    newFragment.setArguments(bundle);
                } else {
                    Log.e("Movie", "Is null");
                }

                //Set transaction.
                android.support.v4.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, newFragment);
                transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
