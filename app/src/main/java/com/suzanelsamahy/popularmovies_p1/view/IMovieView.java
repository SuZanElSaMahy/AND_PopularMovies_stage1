package com.suzanelsamahy.popularmovies_p1.view;

import com.suzanelsamahy.popularmovies_p1.model.Movie;

import java.util.List;

/**
 * Created by SuZan ElsaMahy on 26-Feb-18.
 */

public interface IMovieView {

    void onPopularMoviesRetrieved(List<Movie> movies);
    void onTopRatedMoviesRetrieved(List<Movie> movies);
    void onMovieRetreieveError(String Message);

}
