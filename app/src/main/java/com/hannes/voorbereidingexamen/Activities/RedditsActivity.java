package com.hannes.voorbereidingexamen.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.hannes.voorbereidingexamen.Adapters.RedditAdapter;
import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.Domein.Subreddit;
import com.hannes.voorbereidingexamen.GSON.RedditDownloader;
import com.hannes.voorbereidingexamen.GSON.SubredditsDownloader;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponse;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponseRedditPosts;
import com.hannes.voorbereidingexamen.Interfaces.OnItemSelectListener;
import com.hannes.voorbereidingexamen.R;
import com.hannes.voorbereidingexamen.Reddit_detail_item;
import com.hannes.voorbereidingexamen.Reddit_list_item;

import java.util.ArrayList;
import java.util.List;

public class RedditsActivity extends ActionBarActivity implements AsyncResponseRedditPosts, OnItemSelectListener{

    ArrayList<RedditPost> posts;
    RedditAdapter aa;


    private boolean isLandscape;


    private Reddit_detail_item detailFragment;

    List<RedditPost> redditsDB = new ArrayList<>();

    String subreddit;

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

        Intent intent = getIntent();
        subreddit = intent.getStringExtra("subreddit");





        Select select = new Select();
        redditsDB = select.all().from(RedditPost.class).execute();
        if (redditsDB.size() > 0){
            posts.clear();
            for (RedditPost p : redditsDB){
                posts.add(p);
            }
            aa.notifyDataSetChanged();
        } else {
            RedditDownloader task = new RedditDownloader();
            task.setDelegate(this);
            task.execute(subreddit);
        }


    }

    @Override
    public void processRedditsFinish(ArrayList<RedditPost> output) {
        Toast.makeText(this, "Databank werd geleegd", Toast.LENGTH_LONG).show();
        new Delete().from(Subreddit.class).execute();

        posts.addAll(output);

        for (RedditPost s : output){
            s.save();
        }
        Toast.makeText(this,"Overzicht werd vernieuwd",Toast.LENGTH_LONG).show();

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subreddits, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_settings :
                posts.clear();
                RedditDownloader task = new RedditDownloader();
                task.setDelegate(this);
                task.execute(subreddit);
                System.out.println("Refresh pushed");
                return true;

            case R.id.search_settings:
                Toast.makeText(this,"This method is not implemented yet",Toast.LENGTH_LONG).show();
                return true;


        }
        return true;

    }
}
