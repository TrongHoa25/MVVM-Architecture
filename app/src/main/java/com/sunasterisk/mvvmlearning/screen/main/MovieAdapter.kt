package com.sunasterisk.mvvmlearning.screen.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.mvvmlearning.R
import com.sunasterisk.mvvmlearning.data.model.Movie
import com.sunasterisk.mvvmlearning.databinding.SingleItemBinding
import com.sunasterisk.mvvmlearning.utils.OnRecyclerViewItemOnClick

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    private var movies = mutableListOf<Movie>()

    private var onRecyclerViewItemOnClick: OnRecyclerViewItemOnClick<Movie>? = null

    fun registerOnclickRecyclerView(listener: OnRecyclerViewItemOnClick<Movie>) {
        onRecyclerViewItemOnClick = listener
    }
    fun setData(movies: List<Movie>) {
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
    override fun getItemCount() = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder =
        MovieHolder(
            DataBindingUtil.inflate<SingleItemBinding>(
                LayoutInflater.from(parent.context),
                R.layout.single_item,
                parent,
                false
            ), onRecyclerViewItemOnClick
        )
    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade)
        holder.bindData(movies[position])
    }

    class MovieHolder(
        private val binding: ViewDataBinding,
        private val listener: OnRecyclerViewItemOnClick<Movie>?
    ): RecyclerView.ViewHolder(binding.root){

        var movie: Movie? = null

        fun bindData(data: Movie) {
            if (binding is SingleItemBinding) {
                binding.itemMovie =
                    ItemMovieViewModel(
                        binding.root,
                        listener!!
                    )
                binding.itemMovie!!.setData(data)
                binding.executePendingBindings()
            }
        }
    }
}
