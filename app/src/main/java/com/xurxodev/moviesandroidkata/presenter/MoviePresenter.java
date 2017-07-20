package com.xurxodev.moviesandroidkata.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.domain.usecase.UseCase;
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
import timber.log.Timber;

/**
 * Created by Usuario on 26/06/2017.
 */

public class MoviePresenter implements Presenter{

    private static final String TAG = MoviePresenter.class.getName();

    private View view;
    private UseCase<List<Movie>> getMoviesUseCase;

    @Inject
    public MoviePresenter(UseCase<List<Movie>> getMoviesUseCase) {
        this.getMoviesUseCase = getMoviesUseCase;
        Timber.tag(TAG);
    }

    public void refresh() {
        if (view.isReady()) {
            view.showLoading();
            view.clearMovies();
            Timber.d("showLoading and clearMovies");
        }

        getMoviesUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e("onError: " + e);
                    }

                    @Override
                    public void onNext(List<Movie> movies) {
                        if (view.isReady()) {
                            view.showMovies(movies);
                            view.showNumMovies(movies.size());
                            Timber.d("showMovies and showNumMovies");
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
