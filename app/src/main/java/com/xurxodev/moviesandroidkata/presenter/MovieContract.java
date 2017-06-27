package com.xurxodev.moviesandroidkata.presenter;

import com.xurxodev.moviesandroidkata.model.Movie;

import java.util.List;

/**
 * Created by Usuario on 26/06/2017.
 */

public interface MovieContract {

    public interface Presenter {
        //List<Movie> getMovies();
        void refresh();
        void setView(View view);
    }

    public interface View {
        void showLoading();
        void showMovies(List<Movie> listMovies);
    }
}