package com.example.pokemon.data.data_source.reomote.retrofit

import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.data.data_source.reomote.retrofit.response.PokemonDetailsRetrofitResponse
import com.example.pokemon.data.data_source.reomote.retrofit.response.PokemonRetrofitResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonRetrofitApiInterface {
    companion object {
        const val PAGE_SIZE = PokemonPageConfig.PAGE_SIZE
    }

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = PAGE_SIZE
    ): PokemonRetrofitResponse

    @GET("pokemon/{id}/")
    suspend fun getPokemonDetails(@Path("id") id: Int): PokemonDetailsRetrofitResponse
}