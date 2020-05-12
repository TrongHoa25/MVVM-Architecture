package com.sunasterisk.mvvmlearning.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sunasterisk.mvvmlearning.R

object BindingUtils {
    @JvmStatic
    @BindingAdapter("image")
    fun image(view: ImageView, url: String) {
        Glide.with(view)
            .load(url)
            .apply(RequestOptions().error(R.drawable.ic_launcher_background))
            .into(view)
    }
}
