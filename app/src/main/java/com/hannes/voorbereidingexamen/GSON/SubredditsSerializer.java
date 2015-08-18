package com.hannes.voorbereidingexamen.GSON;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hannes.voorbereidingexamen.Domein.Subreddit;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by hannes on 18/08/15.
 */
public class SubredditsSerializer implements JsonDeserializer<ArrayList<Subreddit>> {
    @Override
    public ArrayList<Subreddit> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ArrayList<Subreddit> subreddits = new ArrayList<Subreddit>();

        JsonObject jobj = json.getAsJsonObject();
        JsonObject data = jobj.get("data").getAsJsonObject();
        JsonArray children = data.get("children").getAsJsonArray();
        //In children[0] zit er een JsonObject met daarin alles wat we nodig hebben
        //De eerste 10 types ophalen
        for (int i = 0; i < 10; i++){
            JsonObject subredditData = children.get(i).getAsJsonObject();
            JsonObject data2 = subredditData.get("data").getAsJsonObject();
            String display_name = data2.get("display_name").getAsString();
            String title = data2.get("title").getAsString();
            String public_description = data2.get("public_description").getAsString();

            Subreddit subreddit = new Subreddit(display_name,title,public_description);
            //subreddit.save();

            subreddits.add(subreddit);
        }

        return subreddits;
    }

}



