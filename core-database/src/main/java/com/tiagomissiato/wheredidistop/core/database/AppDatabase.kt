package com.tiagomissiato.wheredidistop.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tiagomissiato.wheredidistop.core.database.dao.MovieDao
import com.tiagomissiato.wheredidistop.core.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
}