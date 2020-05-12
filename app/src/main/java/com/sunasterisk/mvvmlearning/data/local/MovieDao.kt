package com.sunasterisk.mvvmlearning.data.local

import androidx.room.*
import com.sunasterisk.mvvmlearning.data.model.Movie
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllRecords(): Observable<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg movie: Movie?): Completable

    @Delete
    fun DeleteMovie(movie: Movie): Completable
}