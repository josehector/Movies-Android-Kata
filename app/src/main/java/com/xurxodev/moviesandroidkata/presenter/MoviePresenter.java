package com.xurxodev.moviesandroidkata.presenter;

import com.xurxodev.moviesandroidkata.presenter.MovieContract.*;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Usuario on 26/06/2017.
 */

public class MoviePresenter implements Presenter {

    private View view;
    private MovieRepository movieRepository;

    @Inject
    public MoviePresenter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public void refresh() {
        view.showLoading();
        view.showMovies(movieRepository.getMovies());
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }
}
