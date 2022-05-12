package com.example.pokemon.data.repoImpl

import com.example.pokemon.data.data_source.reomote.PokemonRetrofitApiInterface
import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.domain.repos.PokemonDetailsRepo

class PokemonDetailsRepoImpl(val pokemonApi:PokemonRetrofitApiInterface):PokemonDetailsRepo {
    override suspend fun getPokemonDetails(id:Int): PokemonDetailsResponse {
        return pokemonApi.getPokemonDetails(id)
    }
}