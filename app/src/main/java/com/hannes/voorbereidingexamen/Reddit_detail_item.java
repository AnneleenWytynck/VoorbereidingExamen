package com.hannes.voorbereidingexamen;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.squareup.picasso.Picasso;

public class Reddit_detail_item extends Fragment {
    private TextView points, title, author;
    private ImageView image;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_reddit_detail_item,container,false);
        points = (TextView) v.findViewById(R.id.redditPoints);
        title = (TextView) v.findViewById(R.id.redditTitle);
        author = (TextView) v.findViewById(R.id.redditAuthor);

        image = (ImageView) v.findViewById(R.id.redditImage);

        return v;

    }

    public void setItem(RedditPost post){
        points.setText(String.format("%.2f",post.getScore()));
        title.setText(post.getTitle());
        author.setText(post.getAuthor());

        Picasso.with(getActivity().getBaseContext()).load(post.getUrl()).into(image);
    }
}
