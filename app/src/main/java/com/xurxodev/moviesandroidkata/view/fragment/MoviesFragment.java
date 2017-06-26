package com.xurxodev.moviesandroidkata.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.xurxodev.moviesandroidkata.MoviesApp;
import com.xurxodev.moviesandroidkata.presenter.MovieContract;
import com.xurxodev.moviesandroidkata.presenter.MoviePresenter;
import com.xurxodev.moviesandroidkata.R;
import com.xurxodev.moviesandroidkata.model.Movie;
import com.xurxodev.moviesandroidkata.view.adapter.MoviesAdapter;

import java.util.List;

import javax.inject.Inject;

public class MoviesFragment extends Fragment implements MovieContract.View {

    @Inject
    MoviePresenter moviePresenter;
    private MoviesAdapter adapter;
    private RecyclerView recyclerView;
    private View rootView;
    private TextView moviesCountTextView;
    private ImageButton refreshButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDagger();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_movies, container, false);

        initializeTitle();
        initializeRefreshButton();
        initializeAdapter();
        initializeRecyclerView();

        loadMovies();

        return rootView;
    }

    private void initializeDagger() {
        ((MoviesApp)getActivity().getApplication()).getMoviesComponent().inject(this);
    }

    private void initializeTitle() {
        moviesCountTextView = (TextView) rootView.findViewById(
                R.id.movies_title_text_view);
    }

    private void initializeRefreshButton(){
        refreshButton = (ImageButton) rootView.findViewById(
                R.id.refresh_button);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMovies();
            }
        });
    }

    private void initializeAdapter() {
        adapter = new MoviesAdapter();
    }

    private void initializeRecyclerView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_movies);
        recyclerView.setAdapter(adapter);
    }

    private void loadMovies() {
        //loadingMovies();
        showLoading();

        AsyncTask<Void,Void,List<Movie>> moviesAsyncTask = new AsyncTask<Void, Void, List<Movie>>() {
            @Override
            protected List<Movie> doInBackground(Void... params) {

                //return movieRepository.getMovies();
                return moviePresenter.getMovies();
            }

            @Override
            protected void onPostExecute(List<Movie> movies) {
                loadedMovies(movies);
            }
        };

        moviesAsyncTask.execute();
    }

    private void loadingMovies(){
        adapter.clearMovies();
        moviesCountTextView.setText(R.string.loading_movies_text);
    }

    private void loadedMovies(List<Movie> movies){
        adapter.setMovies(movies);
        refreshTitleWithMoviesCount(movies);
    }

    private void refreshTitleWithMoviesCount(List<Movie> movies) {
        String countText = getString(R.string.movies_count_text);

        moviesCountTextView.setText(String.format(countText, movies.size()));
    }

    @Override
    public void showLoading() {
        loadingMovies();
    }

    @Override
    public void hiddenLoading() {
        //// FIXME: eliminar??
    }

    @Override
    public void showMovies(List<Movie> listMovies) {
        loadedMovies(listMovies);
    }
}
