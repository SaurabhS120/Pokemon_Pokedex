package com.example.pokemon.domain.repo_provider

import com.example.pokemon.data.repoImpl.local.PokemonRoomDatabaseRepo
import com.example.pokemon.data.repoImpl.remote.PokemonRetrofitRepo
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoProvider {
    @Binds
    fun getLocalRepo(pokemonListLocalRepo: PokemonRoomDatabaseRepo): PokemonListLocalRepo
    @Binds
    fun getRemoteRepo(pokemonRemoteRepo: PokemonRetrofitRepo): PokemonRemoteRepo
}