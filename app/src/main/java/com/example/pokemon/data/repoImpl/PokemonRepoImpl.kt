package com.example.pokemon.data.repoImpl

import com.example.pokemon.data.data_source.reomote.PokemonApiInterface
import com.example.pokemon.data.model.PokemonResponse
import com.example.pokemon.domain.repos.PokemonRepo

class PokemonRepoImpl(val pokemonApi : PokemonApiInterface) : PokemonRepo {
    override suspend fun getPokemonList(pageNo: Int): PokemonResponse {
        return pokemonApi.getPokemonList(PokemonPageConfig.PAGE_SIZE*pageNo)
    }

}