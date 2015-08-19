package com.hannes.voorbereidingexamen.Activities;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
        Toast.makeText(this,"Databank werd geleegd",Toast.LENGTH_LONG).show();
        new Delete().from(Subreddit.class).execute();

        //Bij de download moet je subreddits bijhouden
        subreddits.addAll(output);
        //Al de opgehaalde subreddits moeten opgeslagen worden in de databank
        for (Subreddit s : output){
            s.save();
        }
        Toast.makeText(this,"Overzicht werd vernieuwd",Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_subreddits,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_settings :
                subreddits.clear();
                SubredditsDownloader task = new SubredditsDownloader();
                task.setDelegate(this);
                task.execute();
                System.out.println("Refresh pushed");
                return true;

            case R.id.search_settings:
                Toast.makeText(this,"This method is not implemented yet",Toast.LENGTH_LONG).show();
                return true;


        }
        return true;

    }
}
