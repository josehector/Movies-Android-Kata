package com.xurxodev.moviesandroidkata.view.di;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xurxodev.moviesandroidkata.Presenter.MovieContract;
import com.xurxodev.moviesandroidkata.Presenter.MoviePresenter;
import com.xurxodev.moviesandroidkata.data.DiskMovieRepository;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Usuario on 26/06/2017.
 */

@Module
public class MoviesModule {

    @Provides
    @Singleton
    public Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public MovieRepository providesDiskMovieRepository(Application application, Gson gson) {
        return new DiskMovieRepository(application,gson);
    }


    @Provides
    @Singleton
    public MovieContract.Presenter providesPresenter(MovieRepository movieRepository) {
        return new MoviePresenter(movieRepository);
    }
}
