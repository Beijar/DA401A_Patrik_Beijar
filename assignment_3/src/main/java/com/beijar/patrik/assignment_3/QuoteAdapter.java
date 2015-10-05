package com.beijar.patrik.assignment_3;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class QuoteAdapter extends BaseAdapter {
    private Context mContext;
    LinkedList<String> mList;

    public QuoteAdapter(Context c, LinkedList<String> list){
        this.mContext = c;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_quote_item, null);
        }
        TextView quoteText = (TextView)convertView.findViewById(R.id.quote_text);

        Object list_pos = mList.get(position);
        quoteText.setText(list_pos.toString());
        Log.i("TEXT INFO", list_pos.toString());

        return convertView;
    }
}
