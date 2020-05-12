package com.sunasterisk.mvvmlearning.utils

interface OnRecyclerViewItemOnClick<T> {
    fun onClick(data: T)
}