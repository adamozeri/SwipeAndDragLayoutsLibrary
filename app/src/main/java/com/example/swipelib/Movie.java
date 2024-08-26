package com.example.swipelib;

import java.util.ArrayList;

public class Movie {

    private String title;
    private String length;
    private String description;
    private ArrayList<String> actors;
    private String director;
    private String imgUrl;

    public Movie(String title, String length, String description, ArrayList<String> actors, String director,String imgUrl) {
        this.title = title;
        this.length = length;
        this.description = description;
        this.actors = actors;
        this.director = director;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getActors() {
        return actors;
    }

    public void setActors(ArrayList<String> actors) {
        this.actors = actors;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
