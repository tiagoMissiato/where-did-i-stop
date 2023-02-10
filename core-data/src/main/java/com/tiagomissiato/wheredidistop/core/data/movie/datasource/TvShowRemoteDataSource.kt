package com.tiagomissiato.wheredidistop.core.data.movie.datasource

import com.tiagomissiato.wheredidistop.core.network.api.TmdbApiService
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(
    private val apiService: TmdbApiService
): TvShowDataSource {
    override suspend fun getPopularTvShowList() = apiService.getPopularTvShow()
}