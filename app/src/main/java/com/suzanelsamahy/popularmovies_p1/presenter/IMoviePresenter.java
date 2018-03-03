package com.suzanelsamahy.popularmovies_p1.presenter;

/**
 * Created by SuZan ElsaMahy on 26-Feb-18.
 */

public interface IMoviePresenter {

    void getPopularMovies(String key);
    void getTopMovies(String key);
    void onDestroy();
}
