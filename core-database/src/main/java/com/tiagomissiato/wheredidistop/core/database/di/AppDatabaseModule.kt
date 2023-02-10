package com.tiagomissiato.wheredidistop.core.database.di

import android.content.Context
import androidx.room.Room
import com.tiagomissiato.wheredidistop.core.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Provides
    fun provideMovieDao(appDatabase: AppDatabase) =
        appDatabase.movieDao()

    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(
            context = appContext,
            AppDatabase::class.java,
            "where-did-i-stop"
        ).build()
}