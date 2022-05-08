package com.example.pokemon.domain.repoImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.data_source.reomote.PokemonApiInterface
import com.example.pokemon.data.data_source.reomote.PokemonRetrofitAPI
import com.example.pokemon.data.repoImpl.PokemonPageConfig
import com.example.pokemon.data.repoImpl.PokemonRepoImpl
import com.example.pokemon.domain.paging_sources.PokemonPagingSource
import com.example.pokemon.domain.repos.PokemonPagingRepo
import com.example.pokemon.domain.repos.PokemonRepo

class PokemonPagingRepoImpl : PokemonPagingRepo {
    val pokemonApi:PokemonApiInterface = PokemonRetrofitAPI.getClient
    val pokemonRepo: PokemonRepo = PokemonRepoImpl(pokemonApi)
    override fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
        pagingSourceFactory = {PokemonPagingSource(pokemonRepo)}
    ).liveData
}