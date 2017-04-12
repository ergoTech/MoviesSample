package com.vararg.moviessample.screens.moviesscreen.router;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.vararg.moviessample.screens.detailsscreen.DetailsScreenActivity;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;

/**
 * Created by vararg on 10.04.2017.
 */

public class MoviesScreenRouterImpl implements MoviesScreenRouter {

    private Activity activity;

    public MoviesScreenRouterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void navigateToDetails(MovieViewModel movie) {
        Toast.makeText(activity, movie.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(activity, DetailsScreenActivity.class);
        intent.putExtra(DetailsScreenActivity.EXTRA_MOVIE, movie);
        activity.startActivity(intent);
    }
}
