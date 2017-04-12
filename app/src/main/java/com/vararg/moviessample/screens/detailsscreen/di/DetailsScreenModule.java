package com.vararg.moviessample.screens.detailsscreen.di;

import com.vararg.moviessample.screens.detailsscreen.DetailsScreenActivity;
import com.vararg.moviessample.screens.detailsscreen.presenter.DetailsScreenPresenter;
import com.vararg.moviessample.screens.detailsscreen.router.DetailsScreenRouter;
import com.vararg.moviessample.screens.detailsscreen.router.DetailsScreenRouterImpl;
import com.vararg.moviessample.screens.moviesscreen.MoviesScreenActivity;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieViewModel;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vararg on 12.04.2017.
 */

@Module
public class DetailsScreenModule {

    private WeakReference<DetailsScreenActivity> weakActivity;
    private MovieViewModel movie;

    public DetailsScreenModule(MovieViewModel movie) {
        this.movie = movie;
    }


    public void setActivity(DetailsScreenActivity activity) {
        this.weakActivity = new WeakReference<>(activity);
    }

    @Provides
    DetailsScreenRouter provideDetailsScreenRouter() {
        DetailsScreenActivity activity = weakActivity.get();
        if (activity != null) return new DetailsScreenRouterImpl(activity);
        else return null;
    }

    @Provides
    @DetailsScreenScope
    DetailsScreenPresenter provideDetailsScreenPresenter() {
        return new DetailsScreenPresenter(movie);
    }
}
