package com.vararg.moviessample.screens.moviesscreen.domain;

import com.vararg.moviessample.data.movie.MovieService;
import com.vararg.moviessample.network.responses.GetMoviesResponse;

import java.util.Collection;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observables.ConnectableObservable;

/**
 * Created by vararg on 10.04.2017.
 */

public class GetMoviesInteractor {

    private MovieService service;
    private MovieMapper mapper;

    private Scheduler subscribeScheduler;
    private Scheduler observeScheduler;
    private CompositeDisposable compositeDisposable;

    public GetMoviesInteractor(Scheduler subscribeOn, Scheduler observeOn, MovieService service, MovieMapper mapper) {
        this.service = service;
        this.mapper = mapper;

        subscribeScheduler = subscribeOn;
        observeScheduler = observeOn;

        compositeDisposable = new CompositeDisposable();
    }

    public void execute(Consumer<Collection<MovieViewModel>> onNextMovies, Consumer<Integer> onNextPage, Consumer<Throwable> onError,
                        int page) {
        ConnectableObservable<GetMoviesResponse> observable = getObservable(page).publish();

        observable.map(response -> mapper.map(response.getMovies()))
                .subscribe(onNextMovies, onError);

        observable.map(response -> convertApiPageForUI(response.getCurrentPage()))
                .subscribe(onNextPage, onError);
        observable.connect();

        compositeDisposable.add(observable.connect());
    }

    protected Observable<GetMoviesResponse> createObservable(int page) {
        return service.getMovies(convertUIPageForApi(page));
    }

    public final void unsubscribe() {
        // call clear() instead of unsubscribe() to be able to manage new subscriptions
        compositeDisposable.clear();
    }

    private Observable<GetMoviesResponse> getObservable(int page) {
        return createObservable(page)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler);
    }

    /**
     * Needed because api starts from 1 page, and UI starts from 0 page
     */
    private static int convertUIPageForApi(int page) {
        return ++page;
    }

    /**
     * Needed because api starts from 1 page, and UI starts from 0 page
     */
    private static int convertApiPageForUI(int page) {
        return --page;
    }
}
