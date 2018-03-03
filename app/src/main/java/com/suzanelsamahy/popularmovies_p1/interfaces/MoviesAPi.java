package com.suzanelsamahy.popularmovies_p1.interfaces;

import com.suzanelsamahy.popularmovies_p1.Utilities.Constants;
import com.suzanelsamahy.popularmovies_p1.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SuZan ElsaMahy on 24-Feb-18.
 */

public interface MoviesAPi {

    @GET(Constants.POPULAR_MOVIES_URL)
    Call<MovieResponse> callPopularMoviesApi(@Query(Constants.API_PARAM) String apiKey) ;

    @GET(Constants.TOP_MOVIES_URL)
    Call<MovieResponse> callTopRatedMoviesApi(@Query(Constants.API_PARAM) String apiKey) ;

}
