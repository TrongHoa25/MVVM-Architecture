package com.sunasterisk.mvvmlearning.screen.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sunasterisk.mvvmlearning.data.model.Movie
import com.sunasterisk.mvvmlearning.data.repository.MovieRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val repository: MovieRepository
) : ViewModel() {
    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadRemoteMovies() {
        var disposable = repository.getRemoteMovies()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    result-> insertMovieToDB(*result.toTypedArray())
                }, {
                }
            )
        compositeDisposable.add(disposable!!)
    }

    fun loadLocalMovies() {
        var disposable = repository.getLocalMovies()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    _movies.value = it
                }, {
                }
            )
        compositeDisposable.add(disposable!!)
    }

    fun insertMovieToDB(vararg movie: Movie) {
            var disposable = repository.inserMovieToDB(*movie)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                    {
                        Log.d("PTH", "Insert Success")
                    }, {
                        Log.d("PTH", "Insert Fail")
                    }
                )
            compositeDisposable.add(disposable)
    }

    fun deleteMovie(movie: Movie, context: Context) {
        var disposable = repository.deleteMovie(movie)
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(
                {
                    Toast.makeText(context, "Delete Success: " + movie.id.toString(), Toast.LENGTH_SHORT).show()
                }, {
                    Toast.makeText(context, "Delete Fail", Toast.LENGTH_SHORT).show()
                }
            )
        compositeDisposable.add(disposable)
    }

    fun onClear() {
        compositeDisposable.clear()
    }
}
