package com.example.di

import com.example.domain.repository.PokemonRepository
import com.example.domain.usecase.GetPokemonDetailUseCase
import com.example.domain.usecase.GetPokemonListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetPokemonListUseCase(
        repository: PokemonRepository
    ): GetPokemonListUseCase = GetPokemonListUseCase(repository)

    @Provides
    @Singleton
    fun provideGetPokemonDetailUseCase(
        repository: PokemonRepository
    ): GetPokemonDetailUseCase = GetPokemonDetailUseCase(repository)
}
