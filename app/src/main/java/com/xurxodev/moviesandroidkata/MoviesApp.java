package com.xurxodev.moviesandroidkata;

import android.app.Application;

import com.xurxodev.moviesandroidkata.view.di.AppModule;
import com.xurxodev.moviesandroidkata.view.di.DaggerMoviesComponent;
import com.xurxodev.moviesandroidkata.view.di.MoviesComponent;
import com.xurxodev.moviesandroidkata.view.di.MoviesModule;

import timber.log.Timber;

/**
 * Created by Usuario on 26/06/2017.
 */

public class MoviesApp extends Application {

    private MoviesComponent moviesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        moviesComponent = DaggerMoviesComponent.builder()
                .appModule(new AppModule(this))
                .moviesModule(new MoviesModule())
                .build();
    }

    public MoviesComponent getMoviesComponent() {
        return moviesComponent;
    }
}
