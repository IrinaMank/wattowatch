package com.example.irina.wtw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Irina on 28.06.2017.
 */

public interface MoviesApiService {
    @GET("search/movie")
    Call<Movie.MovieResult> searchMovie(@Query("api_key") String key, @Query("query") String title);
    @GET("movie/popular")
    Call<Movie.MovieResult> getPopularMovies(@Query("api_key") String key);
}


