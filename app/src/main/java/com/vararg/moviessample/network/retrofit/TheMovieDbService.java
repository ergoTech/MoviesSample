package com.vararg.moviessample.network.retrofit;

import com.vararg.moviessample.network.responses.GetMoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vararg on 10.04.2017.
 */

public interface TheMovieDbService {

    @GET("movie/now_playing")
    Observable<GetMoviesResponse> getMovies(@Query("api_key") String apiKey, @Query("page") int page);

}
