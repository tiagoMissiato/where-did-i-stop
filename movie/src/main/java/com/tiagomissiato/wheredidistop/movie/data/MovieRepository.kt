package com.tiagomissiato.wheredidistop.movie.data

import com.tiagomissiato.wheredidistop.core.database.dao.MovieDao
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.api.TmdbApiService
import com.tiagomissiato.wheredidistop.movie.data.model.Movie
import com.tiagomissiato.wheredidistop.movie.domain.MovieRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: TmdbApiService,
    private val localDataSource: MovieDao
): MovieRepository {

    override fun getPopularMovies() = flow {
        val list = when (val response = remoteDataSource.getPopularMovies()) {
            is NetworkResult.Error -> getCachedPopularMovies()
            is NetworkResult.Exception -> getCachedPopularMovies()
            is NetworkResult.Success -> {

                val remoteData = response.data.list.map { it.toEntity() }

                localDataSource.deleteAll()
                localDataSource.insertAll(remoteData)

                getCachedPopularMovies()
            }
        }
        
        emit(list)
    }

    override suspend fun getRemotePopularMovies(): List<Movie> =
        when (val response = remoteDataSource.getPopularMovies()) {
            is NetworkResult.Error -> getCachedPopularMovies()
            is NetworkResult.Exception -> getCachedPopularMovies()
            is NetworkResult.Success -> {

                val remoteData = response.data.list.map { it.toEntity() }

                localDataSource.deleteAll()
                localDataSource.insertAll(remoteData)

                getCachedPopularMovies()
            }
        }

    override suspend fun getCachedPopularMovies(): List<Movie> =
        localDataSource
            .getAll()
            .map { it.toDto() }

    override suspend fun clearCache() {
        localDataSource.deleteAll()
    }
}