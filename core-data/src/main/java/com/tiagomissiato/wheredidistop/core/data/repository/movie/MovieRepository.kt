package com.tiagomissiato.wheredidistop.core.data.repository

import com.tiagomissiato.wheredidistop.core.data.mapper.toDto
import com.tiagomissiato.wheredidistop.core.data.mapper.toEntity
import com.tiagomissiato.wheredidistop.core.data.movie.datasource.MovieLocalDataSource
import com.tiagomissiato.wheredidistop.core.data.movie.datasource.MovieRemoteDataSource
import com.tiagomissiato.wheredidistop.core.domain.repository.movie.MovieRepository
import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResultException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
): MovieRepository {

    override suspend fun getRemotePopularMovies(): List<Movie> =
        when (val response = remoteDataSource.getPopularMovieList()) {
            is NetworkResult.Error -> throw NetworkResultException.Error(
                code = response.code,
                msg = response.message
            )
            is NetworkResult.Exception -> throw NetworkResultException.Exception(response.e)
            is NetworkResult.Success -> {
                val listMovie = response.data.list.map { it.toDto() }

                localDataSource.insertPopularMovieList(
                    listMovie.map { it.toEntity() }
                )

                listMovie
            }
        }

    override suspend fun getCachedPopularMovies(): List<Movie> =
        localDataSource
            .getPopularMovieList()
            .map { it.toDto() }

    override suspend fun clearCache() {
        localDataSource.clearCache()
    }
}