package com.hannes.voorbereidingexamen.Domein;

/**
 * Created by hannes on 18/08/15.
 */
public class RedditPost {
    private String author;
    private Double score;
    private String title;
    private String url;

    public RedditPost(String author, Double score, String title, String url) {
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
