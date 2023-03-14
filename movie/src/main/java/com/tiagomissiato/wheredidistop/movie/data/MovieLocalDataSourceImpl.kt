package com.tiagomissiato.wheredidistop.movie.data

import com.tiagomissiato.wheredidistop.core.database.dao.MovieDao
import com.tiagomissiato.wheredidistop.core.database.entity.MovieEntity
import javax.inject.Inject

interface MovieLocalDataSource {
    suspend fun getPopularMovieList(): List<MovieEntity>
    suspend fun insertPopularMovieList(movies : List<MovieEntity>)
    suspend fun clearCache()
}

class MovieLocalDataSourceImpl @Inject constructor(private val movieDao: MovieDao) :
    MovieLocalDataSource {

    override suspend fun getPopularMovieList() =
        movieDao.getAll()

    override suspend fun insertPopularMovieList(movies: List<MovieEntity>) =
        movieDao.insertAll(movies)

    override suspend fun clearCache() {
        movieDao.deleteAll()
    }
}