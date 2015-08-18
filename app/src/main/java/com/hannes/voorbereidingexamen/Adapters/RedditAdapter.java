package com.hannes.voorbereidingexamen.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.R;

import java.util.ArrayList;

/**
 * Created by hannes on 18/08/15.
 */
public class RedditAdapter extends BaseAdapter {
    ArrayList<RedditPost> redditPosts;

    public RedditAdapter(ArrayList<RedditPost> redditPosts) {
        this.redditPosts = redditPosts;
    }

    @Override
    public int getCount() {
        return redditPosts.size();
    }

    @Override
    public Object getItem(int position) {
        return redditPosts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.fragment_reddit_list_item,parent,false);
        }

        TextView postName = (TextView) convertView.findViewById(R.id.redditTitle);
        TextView postAuthor = (TextView) convertView.findViewById(R.id.author);
        TextView postPoints = (TextView) convertView.findViewById(R.id.points);

        RedditPost post = redditPosts.get(position);

        postName.setText(post.getTitle());
        postAuthor.setText(post.getAuthor());
        postPoints.setText(String.format("%.2f points", post.getScore()));

        return convertView;
    }
}
