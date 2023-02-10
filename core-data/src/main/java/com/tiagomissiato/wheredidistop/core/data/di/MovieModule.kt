package com.tiagomissiato.wheredidistop.core.data.di

import com.tiagomissiato.wheredidistop.core.data.movie.datasource.MovieLocalDataSource
import com.tiagomissiato.wheredidistop.core.data.movie.datasource.MovieLocalDataSourceImpl
import com.tiagomissiato.wheredidistop.core.data.repository.MovieRepository
import com.tiagomissiato.wheredidistop.core.data.repository.MovieRepositoryImpl
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