package com.example.pokemon.data.data_source.reomote.retrofit

import com.example.pokemon.data.data_source.reomote.PokemonRemoteRepo

object PokemonRetrofitRepoProvider {
    fun getPokemonRepo():PokemonRemoteRepo=PokemonRetrofitRepo()
}