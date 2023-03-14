package com.tiagomissiato.wheredidistop.core.domain.repository.movie

import com.tiagomissiato.wheredidistop.core.model.dto.Movie

interface MovieRepository {
    suspend fun getRemotePopularMovies(): List<Movie>
    suspend fun getCachedPopularMovies(): List<Movie>
    suspend fun clearCache()
}