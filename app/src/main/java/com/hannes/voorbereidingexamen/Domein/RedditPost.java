package com.hannes.voorbereidingexamen.Domein;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by hannes on 18/08/15.
 */
@Table(name="redditpost")
public class RedditPost extends Model {

    @Column(name="author")
    private String author;
    @Column(name="score")
    private Double score;
    @Column(name="title")
    private String title;
    @Column(name="url")
    private String url;

    public RedditPost(){
        super();
    }

    public RedditPost(String author, Double score, String title, String url) {
        super();
        this.author = author;
        this.score = score;
        this.title = title;
        this.url = url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
