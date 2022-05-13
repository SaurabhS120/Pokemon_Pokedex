package com.example.pokemon.data.repoImpl.paging.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.data_source.mediator.PokemonRemoteMediator
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.domain.repos.PokemonListPagingRepo
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagingApi::class)
class PokemonListPagingRepoImpl(
    val localRepo: PokemonListLocalRepo,
    val remoteRepo : PokemonRemoteRepo,
    val viewModelScope: CoroutineScope
) : PokemonListPagingRepo {
//    val pokemonApi:PokemonApiInterface = PokemonRetrofitAPI.getClient
//    val pokemonRepo: PokemonRepo = PokemonRepoImpl(pokemonApi)
//    override fun getPokemons() = Pager(
//        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
//        pagingSourceFactory = {PokemonPagingSource(pokemonRepo)}
//    ).liveData
    override fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
                remoteMediator = PokemonRemoteMediator(localRepo, remoteRepo,viewModelScope),
        pagingSourceFactory = { localRepo.pagingSource() }
    ).liveData
}