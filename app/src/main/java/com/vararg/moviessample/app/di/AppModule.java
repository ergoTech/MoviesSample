package com.vararg.moviessample.app.di;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vararg on 10.04.2017.
 */

@Module
public class AppModule {

    @Provides
    @Main
    static Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Job
    static Scheduler provideJobScheduler() {
        return Schedulers.computation();
    }
}
