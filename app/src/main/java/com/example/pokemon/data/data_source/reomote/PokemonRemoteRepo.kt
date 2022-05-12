package com.example.pokemon.data.data_source.reomote

import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonRemoteRepo {
    suspend fun getPokemonList(pageNo: Int):PokemonResponse
    suspend fun getPokemonDetails(id:Int) : PokemonDetailsResponse
}