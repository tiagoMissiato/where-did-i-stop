package com.tiagomissiato.wheredidistop.core.domain.di

import com.tiagomissiato.wheredidistop.core.domain.movie.GetPopularTvShowListUseCase
import com.tiagomissiato.wheredidistop.core.domain.movie.GetPopularTvShowListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TvShowUseCaseModule {

    @Singleton
    @Binds
    fun bindPopularTvShowUseCase(
        useCase: GetPopularTvShowListUseCaseImpl
    ): GetPopularTvShowListUseCase

}