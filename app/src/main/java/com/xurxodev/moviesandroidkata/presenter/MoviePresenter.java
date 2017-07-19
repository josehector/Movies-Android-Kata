package com.xurxodev.moviesandroidkata.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presenter.MovieContract.*;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Usuario on 26/06/2017.
 */

public class MoviePresenter implements Presenter{

    private static final String TAG = MoviePresenter.class.getName();

    private View view;
    private GetMoviesUseCase getMoviesUseCase;

    @Inject
    public MoviePresenter(GetMoviesUseCase getMoviesUseCase) {
        this.getMoviesUseCase = getMoviesUseCase;
    }

    public void refresh() {

        getMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Action1<List<Movie>>() {
                    @Override
                    public void call(List<Movie> movies) {
                        if (view.isReady()) {
                            view.showLoading();
                            view.clearMovies();
                            Log.d(TAG, "showLoading and clearMovies");
                        }
                    }
                })
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        if (view.isReady()) {
                            view.showMovies(movies);
                            view.showNumMovies(movies.size());
                            Log.d(TAG, "showMovies and showNumMovies");
                        }

                    }
                });

    }

    //@Override
    public void refresh2() {
        AsyncTask<Void,Void,List<Movie>> moviesAsyncTask = new AsyncTask<Void, Void, List<Movie>>() {

            @Override
            protected void onPreExecute() {
                if (view.isReady()) {
                    view.showLoading();
                    view.clearMovies();
                }
            }

            @Override
            protected List<Movie> doInBackground(Void... params) {
                //return getMoviesUseCase.getMovies();
                return new ArrayList<Movie>();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                if (view.isReady()) {
                    view.showMovies(movies);
                    view.showNumMovies(movies.size());
                }
            }
        };

        moviesAsyncTask.execute();
    }

    @Override
    public void setView(View view) {
        this.view = view;
    }

}
