package com.xurxodev.moviesandroidkata.presenter;

import android.os.AsyncTask;

import com.xurxodev.moviesandroidkata.presenter.MovieContract.*;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Usuario on 26/06/2017.
 */

public class MoviePresenter implements Presenter{

    private View view;
    private MovieRepository movieRepository;

    @Inject
    public MoviePresenter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public void refresh() {
        AsyncTask<Void,Void,List<Movie>> moviesAsyncTask = new AsyncTask<Void, Void, List<Movie>>() {

            @Override
            protected void onPreExecute() {
                if (view.isReady()) {
                    view.showLoading();
                    view.clearMovies();
                }
            }

            @Override
            protected List<Movie> doInBackground(Void... params) {
                return movieRepository.getMovies();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                if (view.isReady()) {
                    view.showMovies(movies);
                    view.showNumMovies(movies.size());
                }
            }
        };

        moviesAsyncTask.execute();
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

}
