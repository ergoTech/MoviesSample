package com.vararg.moviessample.screens.detailsscreen.presenter;

import android.util.Log;

import com.vararg.moviessample.screens.detailsscreen.router.DetailsScreenRouter;
import com.vararg.moviessample.screens.detailsscreen.view.DetailsScreenCallbacks;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;

/**
 * Created by vararg on 12.04.2017.
 */

public class DetailsScreenPresenter {

    private static String TAG = DetailsScreenPresenter.class.getSimpleName();

    private MovieViewModel cachedMovie;

    private DetailsScreenRouter router;

    private DetailsScreenCallbacks view;

    public DetailsScreenPresenter(MovieViewModel movie) {
        this.cachedMovie = movie;
    }

    public void dropView() {
        this.view = null;
        onDropView();
    }

    protected void onDropView() {

    }

    public void takeView(DetailsScreenCallbacks view) {
        this.view = view;
        onTakeView(view);
    }

    protected void onTakeView(DetailsScreenCallbacks view) {
        if (cachedMovie != null) {
            view.onMovieReceived(cachedMovie);
        }
    }

    public void takeRouter(DetailsScreenRouter router) {
        this.router = router;
        onTakeRouter(router);
    }

    public void dropRouter() {
        this.router = null;
        onDropRouter();
    }

    protected void onDropRouter() {

    }

    protected void onTakeRouter(DetailsScreenRouter router) {

    }

    public void onBackPressed() {
        router.navigateBack();
    }

    public void fetchData() {
        view.onMovieReceived(cachedMovie);
    }

}
