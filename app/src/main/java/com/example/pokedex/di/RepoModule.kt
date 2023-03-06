package com.example.pokedex.di

import com.example.pokedex.network.PokemonRepository
import com.example.pokedex.network.PokemonRepositoryImp
import com.example.pokedex.network.RetroApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(retroAPI: RetroApiService): PokemonRepository {
        return PokemonRepositoryImp(retroAPI)
    }
}