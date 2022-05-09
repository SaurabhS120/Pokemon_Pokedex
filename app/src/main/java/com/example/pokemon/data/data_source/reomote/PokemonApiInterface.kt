package com.example.pokemon.data.data_source.reomote

import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.data.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiInterface {
    companion object{
        val PAGE_SIZE = 10
    }
    @GET("pokemon/")
    suspend fun getPokemonList(@Query("offset")offset:Int,@Query("limit")limit:Int = PAGE_SIZE): PokemonResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(@Path("id")id:Int) : PokemonDetailsResponse
}