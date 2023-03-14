package com.tiagomissiato.wheredidistop.movie.di

import com.tiagomissiato.wheredidistop.movie.data.MovieLocalDataSource
import com.tiagomissiato.wheredidistop.movie.data.MovieLocalDataSourceImpl
import com.tiagomissiato.wheredidistop.movie.data.MovieRepositoryImpl
import com.tiagomissiato.wheredidistop.movie.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MovieModule {

    @Singleton
    @Binds
    fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @Singleton
    @Binds
    fun bindMovieLocalDataSource(dataSource: MovieLocalDataSourceImpl): MovieLocalDataSource

}