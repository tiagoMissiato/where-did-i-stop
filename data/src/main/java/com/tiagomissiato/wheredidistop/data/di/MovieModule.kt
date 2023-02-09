package com.tiagomissiato.wheredidistop.data.di

import com.tiagomissiato.wheredidistop.data.repository.MovieRepository
import com.tiagomissiato.wheredidistop.data.repository.MovieRepositoryImpl
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

}