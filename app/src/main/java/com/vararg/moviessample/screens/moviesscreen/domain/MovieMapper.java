package com.vararg.moviessample.screens.moviesscreen.domain;

import com.vararg.moviessample.data.movie.Movie;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by vararg on 10.04.2017.
 */

public class MovieMapper {

    public Collection<MovieViewModel> map(Collection<Movie> in) {
        Collection<MovieViewModel> out = new ArrayList<>(in.size());

        for (Movie movie : in) {
            out.add(new MovieViewModel(movie.getId(), movie.isAdult(), movie.getOverview(),
                    movie.getReleaseDate(), movie.getTitle(), movie.getOriginalTitle(),
                    movie.getScore(), movie.getPosterUrl()));
        }

        return out;
    }

}
