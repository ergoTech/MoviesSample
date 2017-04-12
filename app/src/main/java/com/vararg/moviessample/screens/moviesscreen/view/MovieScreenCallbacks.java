package com.vararg.moviessample.screens.moviesscreen.view;

import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;

import java.util.Collection;

/**
 * Created by vararg on 10.04.2017.
 */

public interface MovieScreenCallbacks {

    void showProgress();

    void hideProgress();

    void showError();

    void onMoviesReceived(Collection<MovieViewModel> movies);

    void onNewMoviesReceived(Collection<MovieViewModel> movies);

}
