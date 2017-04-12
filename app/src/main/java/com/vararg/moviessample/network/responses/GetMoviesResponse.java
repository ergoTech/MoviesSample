package com.vararg.moviessample.network.responses;

import com.google.gson.annotations.SerializedName;
import com.vararg.moviessample.data.movie.Movie;

import java.util.List;

/**
 * Created by vararg on 10.04.2017.
 */

public class GetMoviesResponse {

    @SerializedName("page")
    private int currentPage;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<Movie> movies;

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
