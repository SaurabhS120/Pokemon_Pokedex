package com.example.pokemon.domain.repoImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.data_source.mediator.PokemonRemoteMediator
import com.example.pokemon.data.data_source.local.PokemonLocalRepo
import com.example.pokemon.data.data_source.reomote.PokemonRemoteRepo
import com.example.pokemon.data.repoImpl.PokemonPageConfig
import com.example.pokemon.domain.repos.PokemonPagingRepo
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagingApi::class)
class PokemonPagingRepoImpl(
    val localRepo: PokemonLocalRepo,
    val remoteRepo : PokemonRemoteRepo,
    val viewModelScope: CoroutineScope
) : PokemonPagingRepo {
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