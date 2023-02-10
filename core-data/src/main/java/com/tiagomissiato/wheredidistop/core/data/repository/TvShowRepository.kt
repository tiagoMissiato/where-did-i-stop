package com.tiagomissiato.wheredidistop.core.data.repository

import com.tiagomissiato.wheredidistop.core.data.movie.datasource.TvShowRemoteDataSource
import com.tiagomissiato.wheredidistop.core.model.dto.TvShow
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResultException
import javax.inject.Inject

interface TvShowRepository {
    suspend fun getPopularTvShowList(): List<TvShow>
}

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