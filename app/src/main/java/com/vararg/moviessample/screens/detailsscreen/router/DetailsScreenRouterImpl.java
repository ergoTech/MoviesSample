package com.vararg.moviessample.screens.detailsscreen.router;

import android.app.Activity;

/**
 * Created by vararg on 12.04.2017.
 */

public class DetailsScreenRouterImpl implements DetailsScreenRouter {

    private Activity activity;

    public DetailsScreenRouterImpl(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void navigateBack() {
        activity.onBackPressed();
    }
}
