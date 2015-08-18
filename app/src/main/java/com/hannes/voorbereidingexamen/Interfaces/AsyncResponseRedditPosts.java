package com.hannes.voorbereidingexamen.Interfaces;

import com.hannes.voorbereidingexamen.Domein.RedditPost;

import java.util.ArrayList;

/**
 * Created by hannes on 18/08/15.
 */
public interface AsyncResponseRedditPosts {
    void processRedditsFinish(ArrayList<RedditPost> output);
}
