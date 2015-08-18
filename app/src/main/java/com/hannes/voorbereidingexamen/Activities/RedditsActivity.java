package com.hannes.voorbereidingexamen.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hannes.voorbereidingexamen.Adapters.RedditAdapter;
import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.Domein.Subreddit;
import com.hannes.voorbereidingexamen.GSON.RedditDownloader;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponse;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponseRedditPosts;
import com.hannes.voorbereidingexamen.Interfaces.OnItemSelectListener;
import com.hannes.voorbereidingexamen.R;
import com.hannes.voorbereidingexamen.Reddit_detail_item;
import com.hannes.voorbereidingexamen.Reddit_list_item;

import java.util.ArrayList;

public class RedditsActivity extends ActionBarActivity implements AsyncResponseRedditPosts, OnItemSelectListener{

    ArrayList<RedditPost> posts;
    RedditAdapter aa;


    private boolean isLandscape;


    private Reddit_detail_item detailFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reddits);

        posts = new ArrayList<>();
        aa = new RedditAdapter(posts);

        FragmentManager fm = getFragmentManager();
        Reddit_list_item list_item = (Reddit_list_item) fm.findFragmentById(R.id.redditTitles);
        list_item.setListAdapter(aa);

        detailFragment =  (Reddit_detail_item) fm.findFragmentById(R.id.redditPostM);
        checkLandscape();


        if (detailFragment == null && isLandscape){
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.redditPostM, new Reddit_detail_item());
            ft.commit();
        }
       /* Intent intent = getIntent();
        String subreddit = intent.getStringExtra("subreddit");*/

        RedditDownloader task = new RedditDownloader();
        task.setDelegate(this);
        task.execute();
    }

    @Override
    public void processRedditsFinish(ArrayList<RedditPost> output) {
        posts.addAll(output);
        aa.notifyDataSetChanged();
    }

    @Override
    public void OnItemSelect(int pos) {
        RedditPost r = posts.get(pos);
        checkLandscape();
        if(isLandscape){
            FragmentManager fm = getFragmentManager();
            detailFragment = (Reddit_detail_item) fm.findFragmentById(R.id.redditPostM);
            detailFragment.setItem(r);
        } else {
            Intent i = new Intent(this, RedditDetailActivity.class);
            i.putExtra("points", r.getScore());
            i.putExtra("author", r.getAuthor());
            i.putExtra("title", r.getTitle());
            i.putExtra("url", r.getUrl());
            startActivity(i);
        }
    }

    private void checkLandscape(){
        isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }
}
