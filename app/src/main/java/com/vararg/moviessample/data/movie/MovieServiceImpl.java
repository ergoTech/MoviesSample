package com.vararg.moviessample.data.movie;

import com.vararg.moviessample.network.responses.GetMoviesResponse;
import com.vararg.moviessample.network.retrofit.TheMovieDbService;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * Created by vararg on 10.04.2017.
 */

public class MovieServiceImpl implements MovieService {

    private static final String API_KEY = "ebea8cfca72fdff8d2624ad7bbf78e4c";

    private TheMovieDbService service;

    public MovieServiceImpl(Retrofit retrofit) {
        service = retrofit.create(TheMovieDbService.class);
    }

    public Observable<GetMoviesResponse> getMovies(int page) {
        return service.getMovies(API_KEY, page);
    }
}
