package com.tiagomissiato.wheredidistop.tvshow.popular.di

import com.tiagomissiato.wheredidistop.tvshow.popular.data.TvShowRepositoryImpl
import com.tiagomissiato.wheredidistop.tvshow.popular.domain.TvShowRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface TvShowModule {

    @Singleton
    @Binds
    fun bindTvShowRepository(repository: TvShowRepositoryImpl): TvShowRepository

}