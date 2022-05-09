package com.example.pokemon.domain.repoImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.data_source.local.PokemonDatabase
import com.example.pokemon.data.data_source.PokemonRemoteMediator
import com.example.pokemon.data.repoImpl.PokemonPageConfig
import com.example.pokemon.domain.repos.PokemonPagingRepo
import com.example.pokemon.domain.repos.PokemonRepo

@OptIn(ExperimentalPagingApi::class)
class PokemonPagingRepoImpl(val database:PokemonDatabase,val networkService:PokemonRepo) : PokemonPagingRepo {
//    val pokemonApi:PokemonApiInterface = PokemonRetrofitAPI.getClient
//    val pokemonRepo: PokemonRepo = PokemonRepoImpl(pokemonApi)
//    override fun getPokemons() = Pager(
//        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
//        pagingSourceFactory = {PokemonPagingSource(pokemonRepo)}
//    ).liveData
    val pokemonDao = database.pokemonDao()
    override fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
                remoteMediator = PokemonRemoteMediator(database, networkService),
        pagingSourceFactory = { pokemonDao.pagingSource() }
    ).liveData
}