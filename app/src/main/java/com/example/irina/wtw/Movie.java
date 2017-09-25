package com.example.irina.wtw;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Irina on 29.06.2017.
 */

public class Movie implements Serializable {
    public static final String TMDB_IMAGE_PATH = "http://image.tmdb.org/t/p/w154";
    public static final String TMDB_BD_PATH = "http://image.tmdb.org/t/p/w500";
    public static final String TMDB_BIG_PATH = "http://image.tmdb.org/t/p/w500";
    private String title;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("overview")
    private String description;

    @SerializedName("backdrop_path")
    private String backdrop;

    private Integer id;
    public Movie(String title, String poster, String description) {
        this.title = title;
        this.poster = poster;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   public String getPoster() {
        return TMDB_IMAGE_PATH + poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBigPoster() {
        return TMDB_BIG_PATH + poster;
    }

    public void setBigPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackdrop() {
        return TMDB_BD_PATH  + backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static class MovieResult {
        private List<Movie> results;

        public List<Movie> getResults() {
            return results;
        }
    }
}
