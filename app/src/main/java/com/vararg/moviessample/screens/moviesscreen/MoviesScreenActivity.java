package com.vararg.moviessample.screens.moviesscreen;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vararg.moviessample.R;
import com.vararg.moviessample.app.App;
import com.vararg.moviessample.screens.moviesscreen.di.MoviesScreenComponent;
import com.vararg.moviessample.screens.moviesscreen.di.MoviesScreenModule;
import com.vararg.moviessample.screens.moviesscreen.presenter.MoviesScreenPresenter;
import com.vararg.moviessample.screens.moviesscreen.router.MoviesScreenRouter;
import com.vararg.moviessample.screens.moviesscreen.view.MoviesScreenView;

public class MoviesScreenActivity extends AppCompatActivity {

    private MoviesScreenView screenView;
    private MoviesScreenModule screenModule;
    private MoviesScreenComponent screenComponent;
    private MoviesScreenPresenter presenter;
    private MoviesScreenRouter router;
    private ScopeHolder scopeHolder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_movies);
        screenView = (MoviesScreenView) findViewById(R.id.moviesScreen);

        scopeHolder = (ScopeHolder) getLastCustomNonConfigurationInstance();
        if (scopeHolder == null) {
            screenModule = new MoviesScreenModule();
            screenComponent = ((App) getApplication()).getComponent().plus(screenModule);
            scopeHolder = new ScopeHolder(screenModule, screenComponent);
        } else {
            screenModule = scopeHolder.module;
            screenComponent = scopeHolder.component;
        }
        screenModule.setActivity(this);
        screenComponent.inject(screenView);

        presenter = screenComponent.mainPresenter();
        router = screenComponent.mainRouter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.takeView(screenView);
        presenter.takeRouter(router);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.dropView();
        presenter.dropRouter();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            screenModule.setActivity(null);
        }
    }

    @Override
    public ScopeHolder onRetainCustomNonConfigurationInstance() {
        return scopeHolder;
    }

    private static class ScopeHolder {
        final MoviesScreenModule module;
        final MoviesScreenComponent component;

        public ScopeHolder(MoviesScreenModule module, MoviesScreenComponent component) {
            this.module = module;
            this.component = component;
        }
    }
}
