package com.example.pokemon.domain.repo_provider

import com.example.pokemon.data.repoImpl.paging.mediator.PokemonListRoomPagingRepoImpl
import com.example.pokemon.data.repoImpl.remote.PokemonRetrofitRepo
import com.example.pokemon.domain.repos.PokemonListPagingMediator
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoProvider {
//    @Binds
//    fun getLocalRepo(pokemonListLocalRepo: PokemonRoomDatabaseRepo): PokemonListLocalRepo

    @Binds
    fun getPokemonListPagingMediator(pokemonListRoomPagingRepoImpl: PokemonListRoomPagingRepoImpl):PokemonListPagingMediator

    @Binds
    fun getRemoteRepo(pokemonRemoteRepo: PokemonRetrofitRepo): PokemonRemoteRepo
}