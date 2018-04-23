package com.example.irina.wtw.services;

import com.example.irina.wtw.model.Movie;
import com.example.irina.wtw.model.Review;
import com.example.irina.wtw.model.Want;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.List;

public interface ReviewStorage {
    void deleteAllReview(OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener);
    List<Review> getAllReviews(OnCompleteListener q);
    void addReview(Review review, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener);
    void deleteReview(Review review, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener);
    void updateReview(Review newReview, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener);
    Review getReview(Integer index);

    void addWant(Want want, OnSuccessListener<DocumentReference> successListener, OnFailureListener failureListener);

}
