package com.example.pokemon.data.data_source.reomote.retrofit

import com.example.pokemon.data.data_source.reomote.PokemonRemoteRepo
import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.data.model.PokemonResponse
import com.example.pokemon.data.repoImpl.PokemonPageConfig
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonRetrofitRepo @Inject constructor() : PokemonRemoteRepo{
    val pokemonApi = PokemonRetrofitAPI.getClient
    override suspend fun getPokemonList(pageNo: Int): PokemonResponse {
        return pokemonApi.getPokemonList(PokemonPageConfig.PAGE_SIZE*pageNo)
    }

    override suspend fun getPokemonDetails(id: Int): PokemonDetailsResponse {
        return pokemonApi.getPokemonDetails(id)
    }

}