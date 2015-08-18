package com.hannes.voorbereidingexamen.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.hannes.voorbereidingexamen.Domein.Subreddit;
import com.hannes.voorbereidingexamen.GSON.SubredditsDownloader;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponse;
import com.hannes.voorbereidingexamen.Interfaces.OnItemSelectListener;
import com.hannes.voorbereidingexamen.R;
import com.hannes.voorbereidingexamen.Adapters.SubredditAdapter;
import com.hannes.voorbereidingexamen.Subreddit_list_item;

import java.util.ArrayList;
import java.util.List;

public class SubredditsActivity extends ActionBarActivity implements AsyncResponse, OnItemSelectListener {

    ArrayList<Subreddit> subreddits = new ArrayList<>();
    SubredditAdapter aa;

    List<Subreddit> subredditsdb = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subreddits);

        aa = new SubredditAdapter(subreddits);

        FragmentManager fm = getFragmentManager();
        Subreddit_list_item list_item = (Subreddit_list_item) fm.findFragmentById(R.id.subredditTitles);
        list_item.setListAdapter(aa);

        //Wat zit er in de databank?
        Select select = new Select();
        subredditsdb = select.all().from(Subreddit.class).execute();
        if (subredditsdb.size() > 0) {
            System.out.println("----------The size is----------" + subredditsdb.size());
            subreddits.clear();
            for (Subreddit s : subredditsdb) {
                subreddits.add(s);
            }
            aa.notifyDataSetChanged();
        } else {
            //Downnloadtask opstarten om reddits binnen te halen
            SubredditsDownloader task = new SubredditsDownloader();
            task.setDelegate(this);
            task.execute();
            System.out.println("----------GEGEVENS KOMEN VANUIT HET INTERNET-----------");
        }




    }

    @Override
    public void processSubredditsFinish(ArrayList<Subreddit> output) {

        //Leeg db dat ze er niet dubbel in staan
        new Delete().from(Subreddit.class).execute();

        //Bij de download moet je subreddits bijhouden
        subreddits.addAll(output);
        //Al de opgehaalde subreddits moeten opgeslagen worden in de databank
        for (Subreddit s : output){
            s.save();
        }
        aa.notifyDataSetChanged();



    }

    @Override
    public void OnItemSelect(int pos) {
        Subreddit subreddit = subreddits.get(pos);
        //Nieuwe activity starten die Alle redditposts toont van die subreddit
        Intent i = new Intent(this,RedditsActivity.class);
        i.putExtra("subreddit", subreddit.getName());
        startActivity(i);
    }
}
