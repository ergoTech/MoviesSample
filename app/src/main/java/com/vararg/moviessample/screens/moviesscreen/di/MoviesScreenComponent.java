package com.vararg.moviessample.screens.moviesscreen.di;

import com.vararg.moviessample.screens.moviesscreen.presenter.MoviesScreenPresenter;
import com.vararg.moviessample.screens.moviesscreen.router.MoviesScreenRouter;
import com.vararg.moviessample.screens.moviesscreen.view.MoviesScreenView;

import dagger.Subcomponent;

/**
 * Created by vararg on 10.04.2017.
 */

@MoviesScreenScope
@Subcomponent(modules = MoviesScreenModule.class)
public interface MoviesScreenComponent {

    void inject(MoviesScreenView view);

    MoviesScreenPresenter mainPresenter();

    MoviesScreenRouter mainRouter();
}
