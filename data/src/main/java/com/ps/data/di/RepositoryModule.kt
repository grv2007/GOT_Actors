package com.ps.data.di

import com.ps.data.remote.datasource.DataSource
import com.ps.data.repository.ActorsRepository
import com.ps.data.repository.ActorsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideActorsRepository(
        dataSource: DataSource
    ): ActorsRepository = ActorsRepositoryImpl(
        dataSource
    )
}