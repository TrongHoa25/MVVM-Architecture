package com.sunasterisk.mvvmlearning.screen.main

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sunasterisk.mvvmlearning.R
import com.sunasterisk.mvvmlearning.data.local.MovieLocalDataSource
import com.sunasterisk.mvvmlearning.data.model.Movie
import com.sunasterisk.mvvmlearning.data.remote.MovieRemoteDataSource
import com.sunasterisk.mvvmlearning.data.repository.MovieRepository
import com.sunasterisk.mvvmlearning.screen.viewmodelfactory.MovieViewModelFactory
import com.sunasterisk.mvvmlearning.utils.OnRecyclerViewItemOnClick
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnRecyclerViewItemOnClick<Movie>{

    private var factory: MovieViewModelFactory? = null
    private var mainViewModel: MainViewModel? = null
    private var repository: MovieRepository? = null
    private var _adapter =
        MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        repository =
            MovieRepository(
                MovieLocalDataSource.getInstance(this)!!,
                MovieRemoteDataSource.getInstance()!!
            )
        factory = MovieViewModelFactory(repository!!)
        mainViewModel = ViewModelProviders.of(this, factory)
            .get(MainViewModel::class.java)
        mainViewModel?.let {
            it.loadRemoteMovies()
            it.loadLocalMovies()
        }
        val animator = recyclerMain.itemAnimator!!
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
        initView()
        mainViewModel!!.movies.observe(
            this,
            Observer {
                    movies ->
                _adapter.setData(movies)
            }
        )
}

    private fun initView() {
        recyclerMain.apply {
            setHasFixedSize(false)
            adapter = _adapter
        }
        _adapter.registerOnclickRecyclerView(this)
    }

    override fun onClick(data: Movie) {
        mainViewModel!!.deleteMovie(data,this)
    }

    override fun onStop() {
        mainViewModel!!.onClear()
        super.onStop()
    }
}

