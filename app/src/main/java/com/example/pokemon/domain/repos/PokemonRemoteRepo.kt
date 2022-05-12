package com.example.pokemon.domain.repos

import com.example.pokemon.data.model.PokemonResponse

interface PokemonRemoteRepo {
    suspend fun getPokemonList(pageNo:Int):PokemonResponse
}