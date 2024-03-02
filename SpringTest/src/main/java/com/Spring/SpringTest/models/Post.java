package com.Spring.SpringTest.models;

import jakarta.persistence.*;

@Entity //анотация сущности
public class Post {
    @Id //анотация айди
    @GeneratedValue(strategy = GenerationType.IDENTITY) // генерация нового ключа вместо авто
    private long id;
    private String title;//,anons,fulltext;
    private String anons;
    private String full_text;
    private int views;
    public Post(){}
   public  Post(String title, String anons,String full_text){
        this.title=title;
        this.anons=anons;
        this.full_text=full_text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getFull_text() {
        return full_text;
    }

    public void setFull_text(String fulltext) {
        this.full_text = fulltext;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
