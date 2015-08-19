package com.hannes.voorbereidingexamen.Interfaces;

import com.hannes.voorbereidingexamen.Domein.RedditPost;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by hannes on 18/08/15.
 */
public interface RedditService {


    //Tijdelijk enkel van funny bijhouden
    @GET("/r/{subreddit}.json")
    ArrayList<RedditPost> getReddits(@Path("subreddit") String subreddit);
}
