package com.vararg.moviessample.data.movie;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by vararg on 10.04.2017.
 */

public class Movie {

    private long id;

    @SerializedName("adult")
    private boolean isAdult;

    private String overview;

    @SerializedName("release_date")
    private Date releaseDate;

    @SerializedName("title")
    private String title;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("vote_average")
    private float score;

    @SerializedName("poster_path")
    private String posterUrl;

    public long getId() {
        return id;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public String getOverview() {
        return overview;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public float getScore() {
        return score;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
