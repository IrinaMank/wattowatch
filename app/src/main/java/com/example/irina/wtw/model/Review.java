package com.example.irina.wtw.model;


import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.PropertyName;

@IgnoreExtraProperties
public class Review {
    @PropertyName("tmdb_id")
    public Integer id;
    @PropertyName("rating")
    public Integer rating;
    @PropertyName("review")
    public String review;

    public Review() {

    }

    public Review(Integer id, Integer rating, String review) {
        this.id = id;
        this.rating = rating;
        this.review = review;
    }
}
