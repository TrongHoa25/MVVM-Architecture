package com.sunasterisk.mvvmlearning.data.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @NonNull
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @ColumnInfo(name = "is_new")
    @SerializedName("is_new")
    val isNew: Int,
    @SerializedName("language")
    val language: String,
    @ColumnInfo(name = "like_percent")
    @SerializedName("like_percent")
    val likePercent: Int,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int
)
