package com.xurxodev.moviesandroidkata.view.boundary;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by Usuario on 26/06/2017.
 */

public interface MovieRepository {
    Observable<List<Movie>> getMovies();
}
