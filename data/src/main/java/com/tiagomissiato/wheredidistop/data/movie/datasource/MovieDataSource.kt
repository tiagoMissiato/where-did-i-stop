package com.tiagomissiato.wheredidistop.data.movie.datasource

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.model.PopularMovieListResponse

interface MovieDataSource {
    suspend fun getPopularMovieList(): NetworkResult<PopularMovieListResponse>
}