package com.udacity.sanchitsharma.newsappsanchit_udacity;

/**
 * Created by sanchitsharma on 10/29/17.
 */

public class NewsClass {


    private String title;
    private String author;
    private String webUrl;
    private String section;
    private String date;

    public NewsClass(String title, String author, String webUrl, String section, String date) {
        this.title = title;
        this.author = author;
        this.webUrl = webUrl;
        this.section = section;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}


