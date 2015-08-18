package com.hannes.voorbereidingexamen.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hannes.voorbereidingexamen.Domein.Subreddit;
import com.hannes.voorbereidingexamen.R;

import java.util.ArrayList;

/**
 * Created by hannes on 18/08/15.
 */
public class SubredditAdapter extends BaseAdapter {
    private ArrayList<Subreddit> subreddits;

    public SubredditAdapter(ArrayList<Subreddit> subreddits) {
        this.subreddits = subreddits;
    }

    @Override
    public int getCount() {
        return subreddits.size();
    }

    @Override
    public Object getItem(int position) {
        return subreddits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_subreddit_list_item,parent,false);

        }

        TextView subredditName = (TextView) convertView.findViewById(R.id.subredditTitle);
        TextView subredditDesc = (TextView) convertView.findViewById(R.id.subredditDesc);

        subredditName.setText(subreddits.get(position).getName());
        subredditDesc.setText(subreddits.get(position).getPublic_description());

        return convertView;
    }
}
