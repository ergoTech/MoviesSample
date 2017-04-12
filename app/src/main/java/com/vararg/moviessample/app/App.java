package com.vararg.moviessample.app;

import android.app.Application;

import com.vararg.moviessample.app.di.AppComponent;
import com.vararg.moviessample.app.di.AppModule;
import com.vararg.moviessample.app.di.DaggerAppComponent;
import com.vararg.moviessample.network.NetworkModule;

/**
 * Created by vararg on 10.04.2017.
 */

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .networkModule(new NetworkModule(Consts.BASE_API_URL))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
