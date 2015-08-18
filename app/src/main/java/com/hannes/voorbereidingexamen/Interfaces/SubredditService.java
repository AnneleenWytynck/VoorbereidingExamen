package com.hannes.voorbereidingexamen.Interfaces;

import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.Domein.Subreddit;

import java.util.ArrayList;

import retrofit.http.GET;

/**
 * Created by hannes on 18/08/15.
 */
public interface SubredditService {

    @GET("/r.json")
    ArrayList<Subreddit> getSubreddits();

}
