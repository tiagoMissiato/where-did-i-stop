package com.tiagomissiato.wheredidistop.core.data.di

import com.tiagomissiato.wheredidistop.core.data.repository.TvShowRepositoryImpl
import com.tiagomissiato.wheredidistop.core.domain.repository.tvshow.TvShowRepository
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