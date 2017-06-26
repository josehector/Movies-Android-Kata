package com.xurxodev.moviesandroidkata.view.di;

import com.xurxodev.moviesandroidkata.view.fragment.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Usuario on 26/06/2017.
 */


@Singleton
@Component(modules = {AppModule.class, MoviesModule.class})
public interface MoviesComponent {
    void inject(MoviesFragment moviesFragment);
}
