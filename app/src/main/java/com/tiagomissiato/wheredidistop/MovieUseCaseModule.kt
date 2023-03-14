package com.tiagomissiato.wheredidistop.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object WhereDidIStopDispatcherModule {

    @Singleton
    @Provides
    fun providerIoDispatcher() = Dispatchers.IO

}