package com.tiagomissiato.wheredidistop.movie.data

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.model.response.PopularMovieResponse

interface MovieDataSource {
    suspend fun getPopularMovieList(): NetworkResult<PopularMovieResponse>
}