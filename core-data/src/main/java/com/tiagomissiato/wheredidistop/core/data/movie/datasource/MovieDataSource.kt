package com.tiagomissiato.wheredidistop.core.data.movie.datasource

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.model.response.PopularMovieResponse

interface MovieDataSource {
    suspend fun getPopularMovieList(): NetworkResult<PopularMovieResponse>
}