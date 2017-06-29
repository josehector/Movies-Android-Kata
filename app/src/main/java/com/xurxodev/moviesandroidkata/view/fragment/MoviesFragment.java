package com.xurxodev.moviesandroidkata.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = MoviesFragment.class.getName();

    @Inject
    MovieContract.Presenter moviePresenter;
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

        moviePresenter.setView(this);
        initializeTitle();
        initializeRefreshButton();
        initializeAdapter();
        initializeRecyclerView();

        moviePresenter.refresh();

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
                Log.d(TAG, "onClick: ");
                moviePresenter.refresh();
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

    @Override
    public void showLoading() {
        Log.d(TAG, "loadingMovies: loading...");
        moviesCountTextView.setText(R.string.loading_movies_text);
    }

    @Override
    public void showMovies(List<Movie> listMovies) {
        Log.d(TAG, "loadedMovies: Show movies");
        adapter.setMovies(listMovies);
    }

    @Override
    public boolean isReady() {
        return isAdded();
    }

    @Override
    public void showNumMovies(int num) {
        String countText = getString(R.string.movies_count_text);
        moviesCountTextView.setText(String.format(countText, num));
    }

    @Override
    public void clearMovies() {
        adapter.clearMovies();
    }

}
