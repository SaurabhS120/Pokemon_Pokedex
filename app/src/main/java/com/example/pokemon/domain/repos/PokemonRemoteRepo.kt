package com.example.pokemon.domain.repos

import com.example.pokemon.data.entity.PokemonDetailsEntity
import com.example.pokemon.data.data_source.reomote.retrofit.response.PokemonResponse

interface PokemonRemoteRepo {
    suspend fun getPokemonList(pageNo: Int): PokemonResponse
    suspend fun getPokemonDetails(id: Int): PokemonDetailsEntity
}