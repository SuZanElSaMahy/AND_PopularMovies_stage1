package com.suzanelsamahy.popularmovies_p1.interfaces;

public interface ApiCallBack {
    void onPopularMovieSuccess(Object object);
    void onFailure(String message);
    void onTopMovieSuccess(Object object);
}
