package com.example.pokemon.data.data_source

import com.example.pokemon.data.data_source.local.PokemonLocalRepo
import com.example.pokemon.data.data_source.local.room.PokemonRoomDatabaseRepo
import com.example.pokemon.data.data_source.reomote.PokemonRemoteRepo
import com.example.pokemon.data.data_source.reomote.retrofit.PokemonRetrofitRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoProvider {
    @Binds
    fun getLocalRepo(pokemonLocalRepo: PokemonRoomDatabaseRepo): PokemonLocalRepo
    @Binds
    fun getRemoteRepo(pokemonLocalRepo: PokemonRetrofitRepo):PokemonRemoteRepo
}