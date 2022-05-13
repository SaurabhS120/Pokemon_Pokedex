package com.example.pokemon.data.data_source.reomote.retrofit

import com.example.pokemon.data.repoImpl.remote.PokemonRetrofitRepo
import com.example.pokemon.domain.repos.PokemonRemoteRepo

object PokemonRetrofitRepoProvider {
    fun getPokemonRepo(): PokemonRemoteRepo = PokemonRetrofitRepo()
}