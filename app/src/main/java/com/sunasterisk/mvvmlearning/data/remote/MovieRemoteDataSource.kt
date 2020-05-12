package com.sunasterisk.mvvmlearning.data.remote

import com.sunasterisk.mvvmlearning.data.MovieDataSource
import com.sunasterisk.mvvmlearning.data.model.Movie
import com.sunasterisk.myimageshow.data.resource.remote.conection.RetrofitClient
import io.reactivex.Observable

class MovieRemoteDataSource(): MovieDataSource.remote {
     var movieService = RetrofitClient().getMovieService()

    override fun getAllMovie(): Observable<List<Movie>>? =
        movieService?.getMovies()

    companion object {
        fun getInstance(): MovieRemoteDataSource? {
            var movieRemoteDataSource: MovieRemoteDataSource? = null
            if (movieRemoteDataSource == null) {
                movieRemoteDataSource = MovieRemoteDataSource()
            }
            return movieRemoteDataSource
        }
    }
}
