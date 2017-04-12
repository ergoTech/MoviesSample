package com.vararg.moviessample.screens.detailsscreen.view;

import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;

/**
 * Created by vararg on 12.04.2017.
 */

public interface DetailsScreenCallbacks {
    void onMovieReceived(MovieViewModel movie);
}
