package com.suzanelsamahy.popularmovies_p1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suzanelsamahy.popularmovies_p1.MainMovieFragment;
import com.suzanelsamahy.popularmovies_p1.R;
import com.suzanelsamahy.popularmovies_p1.Utilities.Constants;
import com.suzanelsamahy.popularmovies_p1.model.Movie;

import java.util.List;

/**
 * Created by SuZan ElsaMahy on 01-Mar-18.
 */

public class MovieRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Movie> moviesList;
    private Context context;
    private MainMovieFragment.OpenMovieDetails listener;


    public MovieRecyclerAdapter(List<Movie> moviesList, Context context, MainMovieFragment.OpenMovieDetails listener) {
        this.moviesList = moviesList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.movie_grid_item, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Movie movie = moviesList.get(position);
        MovieViewHolder itemViewHolder = (MovieViewHolder) holder;
        itemViewHolder.title.setText(movie.getTitle());
        Picasso.with(context)
                .load(Constants.IMAGE_URL + movie.getPosterPath())
                .into(itemViewHolder.image);
          itemViewHolder.image.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  listener.openMovieDetails(position);
              }
          });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView image;

        public MovieViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.item_movie_tv);
            image = (ImageView) view.findViewById(R.id.item_movie_imageView);
        }
    }


    public void updateMovies(List<Movie> movies) {
        this.moviesList.clear();
        this.moviesList.addAll(movies);
        notifyDataSetChanged();
    }

}
