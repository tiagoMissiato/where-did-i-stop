package com.tiagomissiato.wheredidistop.tvshow.popular.data

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.model.response.PopularTvShowResponse

interface TvShowDataSource {
    suspend fun getPopularTvShowList(): NetworkResult<PopularTvShowResponse>
}