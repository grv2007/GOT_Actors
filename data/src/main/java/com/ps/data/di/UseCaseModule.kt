package com.ps.data.di

import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.repository.ActorsRepository
import com.ps.data.usecase.GetActorsUseCaseImpl
import com.ps.domain.usecase.GetActorsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideActorsUseCase(
        getActorsRepository: ActorsRepository,
        actorsDomainModelMapper: ActorsDomainModelMapper
    ): GetActorsUseCase = GetActorsUseCaseImpl(
        getActorsRepository,
        actorsDomainModelMapper,
    )
}