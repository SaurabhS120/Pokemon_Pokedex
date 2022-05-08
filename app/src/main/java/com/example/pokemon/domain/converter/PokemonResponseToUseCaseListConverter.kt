package com.example.pokemon.domain.converter

import com.example.pokemon.data.model.PokemonResponse
import com.example.pokemon.domain.usecases.PokemonUseCase

object PokemonResponseToUseCaseListConverter : Converter<PokemonResponse,List<PokemonUseCase>> {
    override fun convert(obj: PokemonResponse): List<PokemonUseCase> {
        return obj.results?.map {
            PokemonUseCase(it.name?:"",it.url?:"")
        } ?: listOf()
    }
}