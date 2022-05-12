package com.example.pokemon.data.repoImpl

import com.example.pokemon.data.data_source.reomote.PokemonRetrofitApiInterface
import com.example.pokemon.data.model.PokemonResponse
import com.example.pokemon.domain.repos.PokemonRemoteRepo

class PokemonRemoteRemoteRepoImpl(val pokemonApi : PokemonRetrofitApiInterface) : PokemonRemoteRepo {
    override suspend fun getPokemonList(pageNo: Int): PokemonResponse {
        return pokemonApi.getPokemonList(PokemonPageConfig.PAGE_SIZE*pageNo)
    }

}