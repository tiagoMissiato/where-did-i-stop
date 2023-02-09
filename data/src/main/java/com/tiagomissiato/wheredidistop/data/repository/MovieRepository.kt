package com.tiagomissiato.wheredidistop.data.repository

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResultException
import com.tiagomissiato.wheredidistop.data.movie.datasource.MovieRemoteDataSource
import com.tiagomissiato.wheredidistop.data.movie.model.MovieDTO
import javax.inject.Inject

interface MovieRepository {
    suspend fun getPopularMovieList(): List<MovieDTO>
}

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
): MovieRepository {

    override suspend fun getPopularMovieList(): List<MovieDTO> {
        return when(val response = remoteDataSource.getPopularMovieList()) {
            is NetworkResult.Error -> throw NetworkResultException.Error(code = response.code, msg = response.message)
            is NetworkResult.Exception -> throw NetworkResultException.Exception(response.e)
            is NetworkResult.Success -> response.data.list.map {
                MovieDTO(
                    id = it.id,
                    adult = it.adult,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    overview = it.overview,
                    posterPath = it.posterPath,
                    releaseDate = it.releaseDate,
                    title = it.title
                )
            }
        }
    }

}