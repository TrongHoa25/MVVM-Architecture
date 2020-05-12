package com.sunasterisk.mvvmlearning.data.repository

import com.sunasterisk.mvvmlearning.data.local.MovieLocalDataSource
import com.sunasterisk.mvvmlearning.data.model.Movie
import com.sunasterisk.mvvmlearning.data.remote.MovieRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable

class MovieRepository(
    private val local: MovieLocalDataSource,
    private val remote: MovieRemoteDataSource
) {
    fun getRemoteMovies() : Observable<List<Movie>>? =
        remote.getAllMovie()

    fun getLocalMovies() : Observable<List<Movie>>? =
        local.getAllMovie()

    fun inserMovieToDB(vararg movie: Movie) : Completable =
        local.insertMovie(*movie)

    fun deleteMovie(movie: Movie): Completable =
        local.deleteMovie(movie)
}
