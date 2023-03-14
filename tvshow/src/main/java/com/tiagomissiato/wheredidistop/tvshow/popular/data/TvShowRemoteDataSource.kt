package com.tiagomissiato.wheredidistop.tvshow.popular.data

import com.tiagomissiato.wheredidistop.core.network.api.TmdbApiService
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(
    private val apiService: TmdbApiService
): TvShowDataSource {
    override suspend fun getPopularTvShowList() = apiService.getPopularTvShow()
}