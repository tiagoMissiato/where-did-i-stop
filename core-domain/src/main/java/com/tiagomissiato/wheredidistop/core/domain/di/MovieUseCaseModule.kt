package com.tiagomissiato.wheredidistop.core.domain.di

import com.tiagomissiato.wheredidistop.core.domain.movie.GetPopularMovieListUseCase
import com.tiagomissiato.wheredidistop.core.domain.movie.GetPopularMovieListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MovieUseCaseModule {

    @Singleton
    @Binds
    fun bindPopularMovieUseCase(
        useCase: GetPopularMovieListUseCaseImpl
    ): GetPopularMovieListUseCase

}