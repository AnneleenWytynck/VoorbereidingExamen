package com.hannes.voorbereidingexamen.Domein;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by hannes on 18/08/15.
 */
@Table(name="subreddit")
public class Subreddit extends Model {

    @Column(name="name")
    private String name;
    @Column(name="title")
    private String title;
    @Column(name="description")
    private String public_description;

    public Subreddit(){
      super();
    }
    public Subreddit(String name, String title, String public_description) {
        super();
        this.name = name;
        this.title = title;
        this.public_description = public_description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublic_description() {
        return public_description;
    }

    public void setPublic_description(String public_description) {
        this.public_description = public_description;
    }
}
