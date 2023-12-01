package com.ps.data.di

import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.remote.datasource.DataSource
import com.ps.data.repository.ActorDetailRepositoryImpl
import com.ps.data.repository.ActorsRepositoryImpl
import com.ps.domain.repository.ActorDetailRepository
import com.ps.domain.repository.ActorsRepository
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
        dataSource: DataSource,
        actorsMapper: ActorsDomainModelMapper
    ): ActorsRepository = ActorsRepositoryImpl(
        dataSource,
        actorsMapper
    )

    @Singleton
    @Provides
    fun provideActorDetailRepository(
        dataSource: DataSource,
        actorDetailMapper: ActorDetailDomainModelMapper
    ): ActorDetailRepository = ActorDetailRepositoryImpl(
        dataSource,
        actorDetailMapper
    )
}