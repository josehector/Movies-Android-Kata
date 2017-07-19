package com.xurxodev.moviesandroidkata.domain.usecase;

import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Usuario on 19/07/2017.
 */

public class GetMoviesUseCase implements UseCase<List<Movie>> {

    private MovieRepository movieRepository;

    public GetMoviesUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Observable<List<Movie>> execute() {
        return movieRepository.getMovies();
    }
}
