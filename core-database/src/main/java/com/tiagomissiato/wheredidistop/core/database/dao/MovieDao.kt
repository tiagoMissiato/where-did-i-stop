package com.tiagomissiato.wheredidistop.core.database.dao

import androidx.room.*
import com.tiagomissiato.wheredidistop.core.database.entity.MovieEntity

@Dao
interface MovieDao{

    @Query("SELECT * FROM movieentity")
    suspend fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(movies: List<MovieEntity>)

    @Delete
    suspend fun delete(movie: MovieEntity)

    @Query("DELETE FROM movieentity")
    suspend fun deleteAll()

}