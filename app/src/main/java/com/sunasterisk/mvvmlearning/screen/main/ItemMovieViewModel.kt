package com.sunasterisk.mvvmlearning.screen.main

import android.R
import android.view.View
import android.view.animation.AnimationUtils
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sunasterisk.mvvmlearning.BR
import com.sunasterisk.mvvmlearning.data.model.Movie
import com.sunasterisk.mvvmlearning.utils.OnRecyclerViewItemOnClick
import com.sunasterisk.mvvmlearning.utils.notNull


class ItemMovieViewModel(
    private val itemView: View,
    private var listener: OnRecyclerViewItemOnClick<Movie>
): BaseObservable() {

    @Bindable
    var movie: Movie? = null

    fun setData(data: Movie) {
        movie = data
        notifyPropertyChanged(BR.movie)
    }

    fun onItemClick() {
        movie.notNull { listener?.onClick(it) }
    }
}
