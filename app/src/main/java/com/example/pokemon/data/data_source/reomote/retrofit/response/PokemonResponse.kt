package com.example.pokemon.data.data_source.reomote.retrofit.response

import com.example.pokemon.data.entity.PokemonListEntity

data class PokemonResponse(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<ResultsItem>? = null
)

class ResultsItem(
    name: String,
    url: String,
    id:Int?
):PokemonListEntity(id, name, url)