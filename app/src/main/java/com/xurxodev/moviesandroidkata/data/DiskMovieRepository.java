package com.xurxodev.moviesandroidkata.data;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class DiskMovieRepository implements MovieRepository{
    private Context applicationContext;

    private Gson gson;

    public DiskMovieRepository(Application applicationContext, Gson gson){
        this.applicationContext = applicationContext;
        this.gson = gson;
    }

    public Observable<List<Movie>> getMovies() {
        String jsonString = null;

        try {
            InputStream inputStream = applicationContext.getResources().openRawResource(R.raw.movies);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);

            jsonString = new String(b);
        } catch (IOException e){
            //TODO: fix io exception
        }

        Movie[] movies = gson.fromJson(jsonString, Movie[].class);

        simulateDelay();

        Observable<List<Movie>> observable = Observable.just(Arrays.asList(movies));

        return observable;
    }

    private void simulateDelay(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
