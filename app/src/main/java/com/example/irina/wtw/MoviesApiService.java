package com.example.irina.wtw;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Irina on 28.06.2017.
 */

public interface MoviesApiService {
    @GET("/search/movie")
    void searchMovie(Callback<Movie.MovieResult> cb);
}

