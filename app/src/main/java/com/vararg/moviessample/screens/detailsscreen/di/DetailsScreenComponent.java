package com.vararg.moviessample.screens.detailsscreen.di;

import com.vararg.moviessample.screens.detailsscreen.presenter.DetailsScreenPresenter;
import com.vararg.moviessample.screens.detailsscreen.router.DetailsScreenRouter;
import com.vararg.moviessample.screens.detailsscreen.view.DetailsScreenView;

import dagger.Subcomponent;

/**
 * Created by vararg on 12.04.2017.
 */

@DetailsScreenScope
@Subcomponent(modules = DetailsScreenModule.class)
public interface DetailsScreenComponent {

    void inject(DetailsScreenView view);

    DetailsScreenPresenter mainPresenter();

    DetailsScreenRouter mainRouter();
}
