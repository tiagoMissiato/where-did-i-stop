package com.tiagomissiato.wheredidistop.core.data.movie.datasource

import com.tiagomissiato.wheredidistop.core.model.response.PopularTvShowResponse
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult

interface TvShowDataSource {
    suspend fun getPopularTvShowList(): NetworkResult<PopularTvShowResponse>
}