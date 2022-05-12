package com.example.pokemon.domain.repoImpl

import com.example.pokemon.data.data_source.reomote.PokemonRemoteRepo
import com.example.pokemon.domain.repos.PokemonListRemoteRepo

class PokemonListRemoteRepoImpl(private val pokemonRemoteRepo: PokemonRemoteRepo) : PokemonListRemoteRepo {
    override suspend fun getPokemonList(pageNo: Int) = pokemonRemoteRepo.getPokemonList(pageNo)
}