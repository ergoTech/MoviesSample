package com.vararg.moviessample.screens.detailsscreen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.vararg.moviessample.R;
import com.vararg.moviessample.app.App;
import com.vararg.moviessample.screens.detailsscreen.di.DetailsScreenComponent;
import com.vararg.moviessample.screens.detailsscreen.di.DetailsScreenModule;
import com.vararg.moviessample.screens.detailsscreen.presenter.DetailsScreenPresenter;
import com.vararg.moviessample.screens.detailsscreen.router.DetailsScreenRouter;
import com.vararg.moviessample.screens.detailsscreen.view.DetailsScreenView;

/**
 * Created by vararg on 11.04.2017.
 */

public class DetailsScreenActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";

    private DetailsScreenView screenView;
    private DetailsScreenModule screenModule;
    private DetailsScreenComponent screenComponent;
    private DetailsScreenPresenter presenter;
    private DetailsScreenRouter router;
    private ScopeHolder scopeHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_details);
        screenView = (DetailsScreenView) findViewById(R.id.detailsScreen);

        scopeHolder = (ScopeHolder) getLastCustomNonConfigurationInstance();
        if (scopeHolder == null) {
            screenModule = new DetailsScreenModule(getIntent().getParcelableExtra(EXTRA_MOVIE));
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
        final DetailsScreenModule module;
        final DetailsScreenComponent component;

        public ScopeHolder(DetailsScreenModule module, DetailsScreenComponent component) {
            this.module = module;
            this.component = component;
        }
    }
}
