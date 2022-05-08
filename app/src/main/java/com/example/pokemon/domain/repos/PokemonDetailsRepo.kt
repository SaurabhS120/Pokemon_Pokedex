package com.example.pokemon.domain.repos

import com.example.pokemon.data.model.PokemonDetailsResponse

interface PokemonDetailsRepo {
    suspend fun getPokemonDetails(id:Int):PokemonDetailsResponse
}