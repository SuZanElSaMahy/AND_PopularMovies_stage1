package com.suzanelsamahy.popularmovies_p1.presenter;

import com.suzanelsamahy.popularmovies_p1.Utilities.ApiClient;
import com.suzanelsamahy.popularmovies_p1.interfaces.ApiCallBack;
import com.suzanelsamahy.popularmovies_p1.model.MovieResponse;
import com.suzanelsamahy.popularmovies_p1.view.IMovieView;

/**
 * Created by SuZan ElsaMahy on 26-Feb-18.
 */

public class MoviePresenter implements IMoviePresenter, ApiCallBack {


    private IMovieView movieView;


    public MoviePresenter(IMovieView movieView){
        this.movieView = movieView;

    }


    @Override
    public void getPopularMovies(String key) {
        ApiClient.getInstance().checkPopularMovieResponse(key,this);
    }

    @Override
    public void getTopMovies(String key) {
        ApiClient.getInstance().checkTopMovieResponse(key,this);
    }

    @Override
    public void onDestroy() {
    movieView = null;
    }



    @Override
    public void onPopularMovieSuccess(Object object) {

        if (movieView == null) {
            return;
        }

        if (object != null && object instanceof MovieResponse) {
            MovieResponse response = (MovieResponse) object;
            if (response.getResults() != null) {
                movieView.onPopularMoviesRetrieved(response.getResults());
            } else {
                movieView.onMovieRetreieveError("Error no Movies");
            }
        } else {
            movieView.onMovieRetreieveError("Error no Response");
        }


    }

    @Override
    public void onFailure(String message) {
        if(movieView != null){
            movieView.onMovieRetreieveError(message);
        }
    }

    @Override
    public void onTopMovieSuccess(Object object) {

        if (movieView == null) {
            return;
        }
        if (object != null && object instanceof MovieResponse) {
            MovieResponse response = (MovieResponse) object;
            if (response.getResults() != null) {
                movieView.onTopRatedMoviesRetrieved(response.getResults());
            } else {
                movieView.onMovieRetreieveError("Error no Movies");
            }
        } else {
            movieView.onMovieRetreieveError("Error no Response");
        }
    }
}
