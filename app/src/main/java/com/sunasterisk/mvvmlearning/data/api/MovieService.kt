package com.sunasterisk.mvvmlearning.data.api

import com.sunasterisk.mvvmlearning.data.model.Movie
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieService {
    @GET("movies")
    fun getMovies(): Observable<List<Movie>>
}