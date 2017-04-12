package com.vararg.moviessample.screens.moviesscreen.presenter;

import android.util.Log;

import com.vararg.moviessample.screens.moviesscreen.domain.GetMoviesInteractor;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;
import com.vararg.moviessample.screens.moviesscreen.router.MoviesScreenRouter;
import com.vararg.moviessample.screens.moviesscreen.view.MovieScreenCallbacks;

import java.util.Collection;

import io.reactivex.functions.Consumer;

/**
 * Created by vararg on 10.04.2017.
 */

public class MoviesScreenPresenter {

    private static String TAG = MoviesScreenPresenter.class.getSimpleName();

    private GetMoviesInteractor moviesInteractor;

    private Collection<MovieViewModel> cachedMovies;
    private int cachedPage;

    private MoviesScreenRouter router;

    private MovieScreenCallbacks view;

    public MoviesScreenPresenter(GetMoviesInteractor moviesInteractor) {
        this.moviesInteractor = moviesInteractor;

        cachedPage = 0;
    }

    public void dropView() {
        this.view = null;
        onDropView();
    }

    protected void onDropView() {
        moviesInteractor.unsubscribe();
    }

    public void takeView(MovieScreenCallbacks view) {
        this.view = view;
        onTakeView(view);
    }

    protected void onTakeView(MovieScreenCallbacks view) {
        if (cachedMovies != null) {
            view.onMoviesReceived(cachedMovies);
        } else {
            fetchStartData();
        }
    }

    public void takeRouter(MoviesScreenRouter router) {
        this.router = router;
        onTakeRouter(router);
    }

    public void dropRouter() {
        this.router = null;
        onDropRouter();
    }

    protected void onDropRouter() {

    }

    protected void onTakeRouter(MoviesScreenRouter router) {

    }

    public void onMovieSelected(MovieViewModel movie) {
        router.navigateToDetails(movie);
    }

    public void fetchStartData() {
        fetchData(movies -> {
            view.hideProgress();
            cachedMovies = movies;
            view.onMoviesReceived(movies);
        }, 0);
    }

    public void fetchNewData() {
        fetchData(movies -> {
            view.hideProgress();
            cachedMovies.addAll(movies);
            view.onNewMoviesReceived(movies);
        }, cachedPage + 1);
    }

    private void fetchData(Consumer<Collection<MovieViewModel>> onMoviesNext, int requestPage) {
        view.showProgress();
        moviesInteractor.execute(onMoviesNext,
                page -> cachedPage = page,
                throwable -> {
                    view.hideProgress();
                    onError("Error while downloading data", throwable);
                }, requestPage);
    }

    private void onError(String string, Throwable throwable) {
        view.showError();
        Log.e(TAG, string, throwable);
    }
}
