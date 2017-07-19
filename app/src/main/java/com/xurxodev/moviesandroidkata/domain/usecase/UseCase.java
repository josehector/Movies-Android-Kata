package com.xurxodev.moviesandroidkata.domain.usecase;

import rx.Observable;

/**
 * Created by Usuario on 19/07/2017.
 */

public interface UseCase<T> {

    Observable<T> execute();
}
