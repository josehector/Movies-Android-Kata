package com.xurxodev.moviesandroidkata.Presenter;

import com.xurxodev.moviesandroidkata.Presenter.MovieContract.Presenter;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.util.List;

/**
 * Created by Usuario on 26/06/2017.
 */

public class MoviePresenter implements Presenter {

    MovieRepository movieRepository;

    public MoviePresenter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.getMovies();
    }

    @Override
    public void refresh() {
        //// FIXME: realmente el refresh es getMovies ????
    }
}
