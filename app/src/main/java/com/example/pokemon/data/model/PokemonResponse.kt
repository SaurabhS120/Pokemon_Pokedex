package com.example.pokemon.data.model

import com.example.pokemon.domain.usecases.PokemonUseCase

data class PokemonResponse(
    val next: String? = null,
    val previous: String? = null,
    val count: Int? = null,
    val results: List<ResultsItem>? = null
)

class ResultsItem(
    name: String,
    url: String
) : PokemonUseCase(name, url) {
    private fun getId(): Int {
        val urlParts = url.split('/')
        return urlParts.get(urlParts.lastIndex - 1).toInt()
    }

    init {
        super.id = getId()
    }
}

