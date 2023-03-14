package com.tiagomissiato.wheredidistop.movie.domain

import com.tiagomissiato.wheredidistop.movie.data.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<List<Movie>>
    suspend fun getRemotePopularMovies(): List<Movie>
    suspend fun getCachedPopularMovies(): List<Movie>
    suspend fun clearCache()
}