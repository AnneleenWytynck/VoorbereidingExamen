package com.hannes.voorbereidingexamen.Interfaces;

import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.Domein.Subreddit;

import java.util.ArrayList;

/**
 * Created by hannes on 18/08/15.
 */
public interface AsyncResponse {
    void processSubredditsFinish(ArrayList<Subreddit> output);


}
