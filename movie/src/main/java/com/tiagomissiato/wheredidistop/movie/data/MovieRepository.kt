package com.tiagomissiato.wheredidistop.movie.data

import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResultException
import com.tiagomissiato.wheredidistop.movie.data.model.Movie
import com.tiagomissiato.wheredidistop.movie.domain.MovieRepository
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