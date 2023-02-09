package com.tiagomissiato.wheredidistop.data.movie.datasource

import com.tiagomissiato.wheredidistop.core.network.api.TmdbApiService
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiService: TmdbApiService
): MovieDataSource {
    override suspend fun getPopularMovieList() = apiService.getPopularMovies()
}