package com.tiagomissiato.wheredidistop.core.data.repository

import com.tiagomissiato.wheredidistop.core.data.movie.datasource.MovieLocalDataSource
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResult
import com.tiagomissiato.wheredidistop.core.network.adapter.NetworkResultException
import com.tiagomissiato.wheredidistop.core.data.movie.datasource.MovieRemoteDataSource
import com.tiagomissiato.wheredidistop.core.database.entity.MovieEntity
import com.tiagomissiato.wheredidistop.core.model.dto.Movie
import javax.inject.Inject

interface MovieRepository {
    suspend fun getPopularMovieList(refresh: Boolean = false): List<Movie>
}

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
): MovieRepository {

    override suspend fun getPopularMovieList(refresh: Boolean): List<Movie> {


        val cachedMovie = if (refresh) {
            localDataSource.clearCache()
            emptyList()
        } else {
            localDataSource
                .getPopularMovieList()
                .map {
                    Movie(
                        id = it.id,
                        adult = it.adult,
                        voteAverage = it.voteAverage,
                        originalLanguage = it.originalLanguage,
                        originalTitle = it.originalTitle,
                        overview = it.overview,
                        posterPath = it.posterPath,
                        releaseDate = it.releaseDate,
                        title = it.title
                    )
                }

        }

        return cachedMovie.ifEmpty {
            when (val response = remoteDataSource.getPopularMovieList()) {
                is NetworkResult.Error -> throw NetworkResultException.Error(
                    code = response.code,
                    msg = response.message
                )
                is NetworkResult.Exception -> throw NetworkResultException.Exception(response.e)
                is NetworkResult.Success -> {
                    val listMovie = response.data.list.map {
                        Movie(
                            id = it.id,
                            adult = it.adult,
                            voteAverage = it.voteAverage,
                            originalLanguage = it.originalLanguage,
                            originalTitle = it.originalTitle,
                            overview = it.overview,
                            posterPath = it.posterPath,
                            releaseDate = it.releaseDate,
                            title = it.title
                        )
                    }

                    localDataSource.insertPopularMovieList(
                        listMovie.map {
                            MovieEntity(
                                id = it.id,
                                adult = it.adult,
                                voteAverage = it.voteAverage,
                                originalLanguage = it.originalLanguage,
                                originalTitle = it.originalTitle,
                                overview = it.overview,
                                posterPath = it.posterPath,
                                releaseDate = it.releaseDate,
                                title = it.title
                            )
                        }
                    )

                    return listMovie
                }
            }
        }
    }

}