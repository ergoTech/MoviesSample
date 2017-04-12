package com.vararg.moviessample.screens.moviesscreen.di;

import com.vararg.moviessample.app.di.Job;
import com.vararg.moviessample.app.di.Main;
import com.vararg.moviessample.data.DataModule;
import com.vararg.moviessample.data.movie.MovieService;
import com.vararg.moviessample.screens.moviesscreen.MoviesScreenActivity;
import com.vararg.moviessample.screens.moviesscreen.domain.GetMoviesInteractor;
import com.vararg.moviessample.screens.moviesscreen.domain.MovieMapper;
import com.vararg.moviessample.screens.moviesscreen.presenter.MoviesScreenPresenter;
import com.vararg.moviessample.screens.moviesscreen.router.MoviesScreenRouter;
import com.vararg.moviessample.screens.moviesscreen.router.MoviesScreenRouterImpl;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

/**
 * Created by vararg on 10.04.2017.
 */

@Module(includes = DataModule.class)
public class MoviesScreenModule {

    private WeakReference<MoviesScreenActivity> weakActivity;

    public void setActivity(MoviesScreenActivity activity) {
        this.weakActivity = new WeakReference<>(activity);
    }

    @Provides
    MoviesScreenRouter provideMoviesScreenRouter() {
        MoviesScreenActivity mainActivity = weakActivity.get();
        if (mainActivity != null) return new MoviesScreenRouterImpl(mainActivity);
        else return null;
    }

    @Provides
    @MoviesScreenScope
    static GetMoviesInteractor provideGetMoviesInteractor(@Job Scheduler jobScheduler, @Main Scheduler mainScheduler,
                                                           MovieService service, MovieMapper mapper) {
        return new GetMoviesInteractor(jobScheduler, mainScheduler, service, mapper);
    }

    @Provides
    @MoviesScreenScope
    static MovieMapper provideMovieMapper() {
        return new MovieMapper();
    }

    @Provides
    @MoviesScreenScope
    static MoviesScreenPresenter provideMovieScreenPresenter(GetMoviesInteractor getMoviesInteractor) {
        return new MoviesScreenPresenter(getMoviesInteractor);
    }
}
