package com.example.pokemon.data.data_source.reomote.retrofit.response

import com.example.pokemon.domain.entities.PokemonListEntity

data class PokemonRetrofitResponse(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<PokemonListRetrofitResultItem>? = null
)

class PokemonListRetrofitResultItem(
    name: String,
    url: String,
    id:Int?
): PokemonListEntity(id, name, url)