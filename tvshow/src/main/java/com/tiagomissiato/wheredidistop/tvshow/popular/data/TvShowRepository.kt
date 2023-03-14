package com.tiagomissiato.wheredidistop.tvshow.popular.data

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResultException
import com.tiagomissiato.wheredidistop.tvshow.popular.data.model.TvShow
import com.tiagomissiato.wheredidistop.tvshow.popular.domain.TvShowRepository
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource
): TvShowRepository {

    override suspend fun getPopularTvShowList(): List<TvShow> {
        return when(val response = remoteDataSource.getPopularTvShowList()) {
            is NetworkResult.Error -> throw NetworkResultException.Error(code = response.code, msg = response.message)
            is NetworkResult.Exception -> throw NetworkResultException.Exception(response.e)
            is NetworkResult.Success -> response.data.list.map {
                TvShow(
                    id = it.id,
                    adult = it.adult,
                    voteAverage = it.voteAverage,
                    originalLanguage = it.originalLanguage,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title
                )
            }
        }
    }

}