package com.suzanelsamahy.popularmovies_p1;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.suzanelsamahy.popularmovies_p1.Utilities.Constants;
import com.suzanelsamahy.popularmovies_p1.adapters.MovieRecyclerAdapter;
import com.suzanelsamahy.popularmovies_p1.model.Movie;
import com.suzanelsamahy.popularmovies_p1.presenter.IMoviePresenter;
import com.suzanelsamahy.popularmovies_p1.presenter.MoviePresenter;
import com.suzanelsamahy.popularmovies_p1.view.IMovieView;
import com.suzanelsamahy.popularmovies_p1.view.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMovieFragment extends Fragment implements IMovieView {


    private List<Movie> pList = null;
    private List<Movie> tList = null;
    private RecyclerView recyclerView;
    private MovieRecyclerAdapter mAdapter;
    private IMoviePresenter moviePresenter;

    public MainMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_movie, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.main_rv);
        moviePresenter = new MoviePresenter(this);
        moviePresenter.getPopularMovies(Constants.API_KEY);
        moviePresenter.getTopMovies(Constants.API_KEY);
        setupRecyclerView(new ArrayList<Movie>());
        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_sort, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.popular:

                item.setChecked(item.isChecked());
                mAdapter.updateMovies(pList);
                Toast.makeText(getActivity(), "popular", Toast.LENGTH_LONG).show();
                return true;
            case R.id.rate:

                item.setChecked(item.isChecked());
                mAdapter.updateMovies(tList);
                Toast.makeText(getActivity(), "rated", Toast.LENGTH_LONG).show();
                return true;

            default:
                break;
        }

        return false;
    }

    @Override
    public void onPopularMoviesRetrieved(List<Movie> movies) {
        pList = movies;

    }

    @Override
    public void onTopRatedMoviesRetrieved(List<Movie> movies) {
        tList = movies;
    }

    @Override
    public void onMovieRetreieveError(String Message) {
        Toast.makeText(getActivity(), "error", Toast.LENGTH_LONG).show();
    }


    private void setupRecyclerView(final List<Movie> movies) {

        mAdapter = new MovieRecyclerAdapter(movies, getActivity(), new OpenMovieDetails() {
            @Override
            public void openMovieDetails(int position) {
                Intent intent = new Intent(getActivity(), MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE,movies.get(position));
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

    }


    public interface OpenMovieDetails {
        void openMovieDetails(int position);
    }


}
