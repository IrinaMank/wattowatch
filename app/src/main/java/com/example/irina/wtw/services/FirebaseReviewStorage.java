package com.example.irina.wtw.services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.irina.wtw.model.Movie;
import com.example.irina.wtw.model.Review;
import com.example.irina.wtw.model.Want;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.Date;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class FirebaseReviewStorage implements ReviewStorage{
    private FirebaseFirestore db;

    public FirebaseReviewStorage() {
        db = FirebaseFirestore.getInstance();
    }
    public void addReview(Review review, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener) {
        db.collection("reviews")
                .add(review)
                .addOnSuccessListener(successListener)
                .addOnFailureListener(failureListener);
    }

    public List<Review> getAllReviews(OnCompleteListener q) {
        db.collection("users")
                .get()
                .addOnCompleteListener(q);
        return null;
    }

    public Review getReview(Integer index) {
        return null;
    }

    public void deleteAllReview(OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener) {

    }

    public void deleteReview(Review review, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener) {

    }

    public void updateReview(Review newReview, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener) {

    }

    public void addWant(Want want, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener) {

        db.collection("wants")
                .add(want)
                .addOnSuccessListener(successListener)
                .addOnFailureListener(failureListener);
    }
}
