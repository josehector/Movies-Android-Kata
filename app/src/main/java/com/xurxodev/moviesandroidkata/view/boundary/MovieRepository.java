package com.xurxodev.moviesandroidkata.view.boundary;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Usuario on 26/06/2017.
 */

public interface MovieRepository {

    @GET("59709c97100000a30471da5d")
    Observable<List<Movie>> getMovies();

    //Call<List<Movie>> getMovies();
}
