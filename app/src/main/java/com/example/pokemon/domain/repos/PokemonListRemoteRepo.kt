package com.example.pokemon.domain.repos

import com.example.pokemon.data.model.PokemonResponse

interface PokemonListRemoteRepo {
    suspend fun getPokemonList(pageNo:Int):PokemonResponse
}