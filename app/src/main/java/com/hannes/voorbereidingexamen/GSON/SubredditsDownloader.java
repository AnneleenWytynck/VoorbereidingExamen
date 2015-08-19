package com.hannes.voorbereidingexamen.GSON;

import android.os.AsyncTask;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponse;
import com.hannes.voorbereidingexamen.Interfaces.SubredditService;
import com.hannes.voorbereidingexamen.Domein.Subreddit;

import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by hannes on 18/08/15.
 */
public class SubredditsDownloader extends AsyncTask<String, Void, ArrayList<Subreddit>> {
    private AsyncResponse delegate = null;

    public void setDelegate(AsyncResponse asyncResponse){
        this.delegate = asyncResponse;
    }

    @Override
    protected ArrayList<Subreddit> doInBackground(String... params) {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ArrayList.class, new SubredditsSerializer())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://www.reddit.com/")
                .setConverter(new GsonConverter(gson))
                .build();


        SubredditService service = restAdapter.create(SubredditService.class);
        return service.getSubreddits();
    }

    @Override
    protected void onPostExecute(ArrayList<Subreddit> subreddits) {
        super.onPostExecute(subreddits);
        delegate.processSubredditsFinish(subreddits);
    }
}
