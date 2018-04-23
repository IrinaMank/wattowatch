package com.example.irina.wtw.model;

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;

import java.util.Date;

@IgnoreExtraProperties
public class Want {
    @PropertyName("tmdbId")
    public Integer id;
    @PropertyName("date")
    public Date date;
    @PropertyName("userId")
    public String userId;

    public Want() {

    }

    public Want(Integer id, Date date, String userId) {
        this.id = id;
        this.date = date;
        this.userId = userId;
    }
}
