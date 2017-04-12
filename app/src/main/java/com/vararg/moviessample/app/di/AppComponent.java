package com.vararg.moviessample.app.di;

import com.vararg.moviessample.screens.detailsscreen.di.DetailsScreenComponent;
import com.vararg.moviessample.screens.detailsscreen.di.DetailsScreenModule;
import com.vararg.moviessample.screens.moviesscreen.di.MoviesScreenComponent;
import com.vararg.moviessample.screens.moviesscreen.di.MoviesScreenModule;
import com.vararg.moviessample.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vararg on 10.04.2017.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    MoviesScreenComponent plus(MoviesScreenModule moviesScreenModule);
    DetailsScreenComponent plus(DetailsScreenModule detailsScreenModule);
}
