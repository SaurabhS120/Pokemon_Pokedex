package com.example.pokemon.domain.repos

import com.example.pokemon.domain.entities.PokemonDetailsEntity
import com.example.pokemon.data.data_source.reomote.retrofit.response.PokemonRetrofitResponse

interface PokemonRemoteRepo {
    suspend fun getPokemonList(pageNo: Int): PokemonRetrofitResponse
    suspend fun getPokemonDetails(id: Int): PokemonDetailsEntity
}