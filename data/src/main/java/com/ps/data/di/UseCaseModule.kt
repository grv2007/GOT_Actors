package com.ps.data.di

import com.ps.data.mapper.ActorDetailDomainModelMapper
import com.ps.data.mapper.ActorsDomainModelMapper
import com.ps.data.repository.ActorDetailRepository
import com.ps.data.repository.ActorsRepository
import com.ps.data.usecase.GetActorDetailUseCaseImpl
import com.ps.data.usecase.GetActorsUseCaseImpl
import com.ps.domain.usecase.GetActorDetailUseCase
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
    @Singleton
    @Provides
    fun provideActorDetailUseCase(
        getActorDetailRepository: ActorDetailRepository,
        actorDetailDomainModelMapper: ActorDetailDomainModelMapper
    ): GetActorDetailUseCase = GetActorDetailUseCaseImpl(
        getActorDetailRepository,
        actorDetailDomainModelMapper,
    )
}