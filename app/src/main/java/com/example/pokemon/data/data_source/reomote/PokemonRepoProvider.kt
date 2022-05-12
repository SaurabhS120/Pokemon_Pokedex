package com.example.pokemon.data.data_source.reomote

import com.example.pokemon.data.repoImpl.PokemonRemoteRemoteRepoImpl
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonRepoProvider {
    @Singleton
    @Provides
    fun getPokemonRepo():PokemonRemoteRepo=PokemonRemoteRemoteRepoImpl(PokemonRetrofitAPI.getClient)
}