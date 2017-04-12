package com.vararg.moviessample.data.movie;

import com.vararg.moviessample.network.responses.GetMoviesResponse;

import io.reactivex.Observable;

/**
 * Created by vararg on 10.04.2017.
 */

public interface MovieService {

    Observable<GetMoviesResponse> getMovies(int page);
}
