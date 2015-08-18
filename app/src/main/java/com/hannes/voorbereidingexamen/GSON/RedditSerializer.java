package com.hannes.voorbereidingexamen.GSON;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hannes.voorbereidingexamen.Domein.RedditPost;
import com.hannes.voorbereidingexamen.Domein.Subreddit;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by hannes on 18/08/15.
 */
public class RedditSerializer implements JsonDeserializer<ArrayList<RedditPost>> {
    @Override
    public ArrayList<RedditPost> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        /*
        *
        *  private String author;
        private Double score;
        private String title;
        private String url;
        *
        *
        * */
        ArrayList<RedditPost> posts = new ArrayList<RedditPost>();

        JsonObject jobj = json.getAsJsonObject();
        JsonObject data = jobj.get("data").getAsJsonObject();
        JsonArray children = data.get("children").getAsJsonArray();
        //In children[0] zit er een JsonObject met daarin alles wat we nodig hebben
        //De eerste 10 types ophalen
        for (int i = 0; i < 10; i++){
            JsonObject subredditData = children.get(i).getAsJsonObject();
            JsonObject data2 = subredditData.get("data").getAsJsonObject();
            System.out.println(data2);
            String author = data2.get("author").getAsString();
            String title = data2.get("title").getAsString();
            String url = data2.get("url").getAsString();
            Double score = data2.get("score").getAsDouble();

            RedditPost post = new RedditPost(author,score,title,url);
            posts.add(post);


        }

        return posts;
    }
}
