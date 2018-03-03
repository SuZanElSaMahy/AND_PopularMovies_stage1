package com.suzanelsamahy.popularmovies_p1.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suzanelsamahy.popularmovies_p1.R;
import com.suzanelsamahy.popularmovies_p1.Utilities.Constants;
import com.suzanelsamahy.popularmovies_p1.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie";
    private TextView title_tv;
    private TextView plotSynposis_tv;
    private TextView releaseDate_tv;
    private TextView avgRate_tv;
    private ImageView poster_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initUI();

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Picasso.with(this)
                .load(Constants.IMAGE_URL+movie.getPosterPath())
                .into(poster_iv);

        title_tv.setText(movie.getTitle());
        avgRate_tv.setText(Double.toString(movie.getVoteAverage()));
        releaseDate_tv.setText(movie.getReleaseDate());
        plotSynposis_tv.setText(movie.getOverview());
        setTitle(movie.getTitle());

    }

    private void initUI(){
        title_tv = (TextView)findViewById(R.id.detail_title_tv);
        plotSynposis_tv =(TextView)findViewById(R.id.plot_tv);
        releaseDate_tv = (TextView)findViewById(R.id.release_date_tv);
        avgRate_tv =(TextView)findViewById(R.id.avg_rate_tv);
        poster_iv =(ImageView)findViewById(R.id.poster_iv);
    }


}
