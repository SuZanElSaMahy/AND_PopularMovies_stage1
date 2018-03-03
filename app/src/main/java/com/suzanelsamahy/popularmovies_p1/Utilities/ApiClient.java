package com.suzanelsamahy.popularmovies_p1.Utilities;

import android.util.Log;

import com.suzanelsamahy.popularmovies_p1.interfaces.ApiCallBack;
import com.suzanelsamahy.popularmovies_p1.interfaces.MoviesAPi;
import com.suzanelsamahy.popularmovies_p1.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SuZan ElsaMahy on 26-Feb-18.
 */

public class ApiClient {

    private static MoviesAPi movieApi = null;
    private static ApiClient apiClient;


    public static synchronized ApiClient getInstance() {
        if (apiClient == null) {
            apiClient = new ApiClient();
            return apiClient;
        } else {
            return apiClient;
        }
    }

    private ApiClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieApi = retrofit.create(MoviesAPi.class);
    }



    public void checkPopularMovieResponse(String key, final ApiCallBack apiCallBack) {
        Callback<MovieResponse> movieResponseCallback = new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                   apiCallBack.onPopularMovieSuccess(response.body());
                } else {
                    apiCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                apiCallBack.onFailure(t.getMessage());
                Log.d("api",""+t.getMessage());
            }
        };

        Call<MovieResponse> getMovieResponseCall = movieApi.callPopularMoviesApi(key);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }


    public void checkTopMovieResponse(String key, final ApiCallBack apiCallBack) {
        Callback<MovieResponse> movieResponseCallback = new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    apiCallBack.onTopMovieSuccess(response.body());
                } else {
                    apiCallBack.onFailure(null);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                t.printStackTrace();
                apiCallBack.onFailure(t.getMessage());
            }
        };

        Call<MovieResponse> getMovieResponseCall = movieApi.callTopRatedMoviesApi(key);
        getMovieResponseCall.enqueue(movieResponseCallback);
    }

}
