package com.sunasterisk.mvvmlearning.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sunasterisk.mvvmlearning.data.model.Movie

@Database(entities = [Movie::class], version = MovieDataBase.DATABASE_VERSION,exportSchema = false)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun MovieDao(): MovieDao

    companion object {
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "moviedb.db"
        @Volatile
        private var databaseInstance: MovieDataBase? = null

        fun getDatabasenIstance(mContext: Context): MovieDataBase =
            databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabaseInstance(mContext).also {
                    databaseInstance = it
                }
            }

        private fun buildDatabaseInstance(context: Context) =
            Room.databaseBuilder(context, MovieDataBase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
