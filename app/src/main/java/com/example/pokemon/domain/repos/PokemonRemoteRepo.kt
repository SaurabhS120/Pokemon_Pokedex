package com.example.pokemon.domain.repos

import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.data.model.PokemonResponse

interface PokemonRemoteRepo {
    suspend fun getPokemonList(pageNo: Int):PokemonResponse
    suspend fun getPokemonDetails(id:Int) : PokemonDetailsResponse
}