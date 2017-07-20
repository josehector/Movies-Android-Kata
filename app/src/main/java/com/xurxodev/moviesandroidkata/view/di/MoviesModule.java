package com.xurxodev.moviesandroidkata.view.di;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xurxodev.moviesandroidkata.domain.usecase.GetMoviesUseCase;
import com.xurxodev.moviesandroidkata.presenter.MovieContract;
import com.xurxodev.moviesandroidkata.presenter.MoviePresenter;
import com.xurxodev.moviesandroidkata.data.DiskMovieRepository;
import com.xurxodev.moviesandroidkata.view.boundary.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

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
    OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        return client.build();
    }

    @Provides
    @Singleton
    public MovieRepository providesDiskMovieRepository(Gson gson, OkHttpClient okHttpClient) {
        //return new DiskMovieRepository(application,gson);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/v2/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        return retrofit.create(MovieRepository.class);
    }



    @Provides
    @Singleton
    public GetMoviesUseCase providesGetMoviesUseCase(MovieRepository movieRepository) {
        return new GetMoviesUseCase(movieRepository);
    }

    @Provides
    @Singleton
    public MovieContract.Presenter providesPresenter(GetMoviesUseCase getMoviesUseCase) {
        return new MoviePresenter(getMoviesUseCase);
    }
}
