package com.hannes.voorbereidingexamen.Activities;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.R;
import com.hannes.voorbereidingexamen.Reddit_detail_item;

public class RedditDetailActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddit_detail);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }
        FragmentManager fm = getFragmentManager();
        Reddit_detail_item detail_item = (Reddit_detail_item) fm.findFragmentById(R.id.redditDetail);

        Bundle data = getIntent().getExtras();

        Double points = data.getDouble("points");
        String author = data.getString("author");
        String title = data.getString("title");
        String url = data.getString("url");

        RedditPost p = new RedditPost(author,points,title,url);
        detail_item.setItem(p);
    }


}
