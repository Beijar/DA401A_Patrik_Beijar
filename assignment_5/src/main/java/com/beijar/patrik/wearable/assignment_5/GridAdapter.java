package com.beijar.patrik.wearable.assignment_5;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private  List<Movie> mMovies;

    public GridAdapter(Context c, List<Movie> movies){
        mContext = c;
        mMovies = movies;
    }

    public int getCount(){
        return mMovies.size();
    }

    public Object getItem(int position){
        return mMovies.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View gridView;
        ImageView imageView;

        if (convertView == null) {
            Movie itemMovie = mMovies.get(position);

            gridView = inflater.inflate(R.layout.grid_item, parent, false);

            TextView titleText = (TextView)gridView.findViewById(R.id.grid_item_title);
            TextView yearText = (TextView)gridView.findViewById(R.id.grid_item_year);

            titleText.setText(itemMovie.getmTitle());
            yearText.setText(itemMovie.getmYear());

            imageView = (ImageView)gridView.findViewById(R.id.grid_item_image);
            imageView.setImageResource(itemMovie.getmThumbn());
        }
        else {
            gridView = convertView;
        }
        return gridView;
    }
}
