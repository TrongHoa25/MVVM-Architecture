package com.sunasterisk.mvvmlearning.screen.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sunasterisk.mvvmlearning.data.repository.MovieRepository
import com.sunasterisk.mvvmlearning.screen.main.MainViewModel

class MovieViewModelFactory(
    private val repository: MovieRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}
