package com.vararg.moviessample.data;

import com.vararg.moviessample.data.movie.MovieService;
import com.vararg.moviessample.data.movie.MovieServiceImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by vararg on 10.04.2017.
 */

@Module
public class DataModule {

    @Provides
    MovieService provideMovieService(Retrofit retrofit) {
        return new MovieServiceImpl(retrofit);
    }

}
