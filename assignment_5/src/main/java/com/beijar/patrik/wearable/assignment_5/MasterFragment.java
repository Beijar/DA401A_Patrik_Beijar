package com.beijar.patrik.wearable.assignment_5;



import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;


public class MasterFragment extends Fragment {
    public static final String FRAGMENT_BUNDLE_KEY = "MOVIE_CHOICE";
    List<Movie> mMovieList = new ArrayList<Movie>();
    GridAdapter mGridAdapter;


    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View v = inflater.inflate(R.layout.fragment_master, container, false);
        final GridView gridview = (GridView) v.findViewById(R.id.gridview);

        mGridAdapter = new GridAdapter(v.getContext(), mMovieList);
        gridview.setAdapter(mGridAdapter);

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
        // Creates function for enabling batch contextual actions
        gridview.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gridview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                int selectCount = gridview.getCheckedItemCount();
                mode.setSubtitle("" + selectCount + " items selected");
                //TODO: FIX COLOR HIGH LIGHT TO SELECTED ITEMS
                if(checked){
                    View tv = (View) gridview.getChildAt(position);
                    tv.setBackgroundColor(Color.MAGENTA);
                }else{
                    View tv = (View) gridview.getChildAt(position);
                    tv.setBackgroundColor(Color.TRANSPARENT);
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_delete:
                        deleteSelectedItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

            private void deleteSelectedItems(){
                // Removes selected griditems from gridview
                SparseBooleanArray checked = gridview.getCheckedItemPositions();
                for (int i = 0; i < checked.size(); i++) {
                    mMovieList.remove(i);
                }
                mGridAdapter.notifyDataSetChanged();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
