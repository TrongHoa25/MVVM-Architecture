package com.sunasterisk.mvvmlearning.data

import com.sunasterisk.mvvmlearning.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable

interface MovieDataSource {
    interface local {
        fun getAllMovie(): Observable<List<Movie>>?
        fun insertMovie(vararg movie: Movie): Completable
        fun deleteMovie(movie: Movie): Completable
    }

    interface remote {
        fun getAllMovie(): Observable<List<Movie>>?
    }
}
