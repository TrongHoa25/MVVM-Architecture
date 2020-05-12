package com.sunasterisk.mvvmlearning.data.local

import android.content.Context
import com.sunasterisk.mvvmlearning.data.MovieDataSource
import com.sunasterisk.mvvmlearning.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable

class MovieLocalDataSource(context: Context): MovieDataSource.local {

    private val movieDataBase = MovieDataBase.getDatabasenIstance(context)

    override fun getAllMovie(): Observable<List<Movie>>? =
        movieDataBase.MovieDao().getAllRecords()

    override fun insertMovie(vararg movie: Movie): Completable =
        movieDataBase.MovieDao().insertUser(*movie)

    override fun deleteMovie(movie: Movie): Completable =
        movieDataBase.MovieDao().DeleteMovie(movie)


    companion object {
        fun getInstance(context: Context): MovieLocalDataSource? {
            var movieLocalDataSource: MovieLocalDataSource? = null
            if (movieLocalDataSource == null) {
                movieLocalDataSource = MovieLocalDataSource(context)
            }
            return movieLocalDataSource
        }
    }
}
