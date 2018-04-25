package com.example.irina.wtw.model;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;

import java.util.Date;

@IgnoreExtraProperties
public class Want {
    @PropertyName("tmdbId")
    public Integer tmdbId;
    @PropertyName("date")
    public Date date;
    @PropertyName("userId")
    public String userId;
    @PropertyName("title")
    public String title;

    public Want() {

    }

    public Want(Integer id, String userId, Date date, String title) {
        this.tmdbId = id;
        this.date = date;
        this.userId = userId;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
