package com.hannes.voorbereidingexamen.GSON;

import android.os.AsyncTask;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.Domein.Subreddit;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponse;
import com.hannes.voorbereidingexamen.Interfaces.AsyncResponseRedditPosts;
import com.hannes.voorbereidingexamen.Interfaces.RedditService;
import com.hannes.voorbereidingexamen.Interfaces.SubredditService;

import java.util.ArrayList;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by hannes on 18/08/15.
 */
public class RedditDownloader extends AsyncTask<String, Void, ArrayList<RedditPost>> {
    private AsyncResponseRedditPosts delegate = null;
    public void setDelegate(AsyncResponseRedditPosts asyncResponse){
        this.delegate = asyncResponse;
    }

    @Override
    protected ArrayList<RedditPost> doInBackground(String... params) {

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(ArrayList.class, new RedditSerializer())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint("https://www.reddit.com/")
                .setConverter(new GsonConverter(gson))
                .build();

        String subreddit = params[0];
        System.out.println(subreddit);

        RedditService service = restAdapter.create(RedditService.class);
        return service.getReddits(subreddit);
    }

    @Override
    protected void onPostExecute(ArrayList<RedditPost> reddits) {
        super.onPostExecute(reddits);
        delegate.processRedditsFinish(reddits);
    }
}
