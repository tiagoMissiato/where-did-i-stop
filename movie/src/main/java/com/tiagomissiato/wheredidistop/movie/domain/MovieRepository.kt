package com.tiagomissiato.wheredidistop.movie.domain

import com.tiagomissiato.wheredidistop.movie.data.model.Movie

interface MovieRepository {
    suspend fun getRemotePopularMovies(): List<Movie>
    suspend fun getCachedPopularMovies(): List<Movie>
    suspend fun clearCache()
}