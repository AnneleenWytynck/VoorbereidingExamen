package com.hannes.voorbereidingexamen.Interfaces;

import com.hannes.voorbereidingexamen.Domein.RedditPost;

import java.util.ArrayList;

import retrofit.http.GET;

/**
 * Created by hannes on 18/08/15.
 */
public interface RedditService {


    //Tijdelijk enkel van funny bijhouden
    @GET("/r/funny.json")
    ArrayList<RedditPost> getReddits();
}
