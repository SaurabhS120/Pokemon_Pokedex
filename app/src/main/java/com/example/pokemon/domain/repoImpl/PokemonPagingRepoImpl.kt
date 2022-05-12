package com.example.pokemon.domain.repoImpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.data_source.local.PokemonDatabase
import com.example.pokemon.data.data_source.PokemonRemoteMediator
import com.example.pokemon.data.data_source.reomote.PokemonRetrofitAPI
import com.example.pokemon.data.repoImpl.PokemonDetailsRepoImpl
import com.example.pokemon.data.repoImpl.PokemonPageConfig
import com.example.pokemon.domain.repos.PokemonPagingRepo
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagingApi::class)
class PokemonPagingRepoImpl(
    val database: PokemonDatabase,
    val networkService: PokemonRemoteRepo,
    val viewModelScope: CoroutineScope
) : PokemonPagingRepo {
//    val pokemonApi:PokemonApiInterface = PokemonRetrofitAPI.getClient
//    val pokemonRepo: PokemonRepo = PokemonRepoImpl(pokemonApi)
//    override fun getPokemons() = Pager(
//        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
//        pagingSourceFactory = {PokemonPagingSource(pokemonRepo)}
//    ).liveData
    val pokemonDao = database.pokemonDao()
    val pokemonDetailsRepo = PokemonDetailsRepoImpl(PokemonRetrofitAPI.getClient)
    override fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
                remoteMediator = PokemonRemoteMediator(database, networkService,pokemonDetailsRepo,viewModelScope),
        pagingSourceFactory = { pokemonDao.pagingSource() }
    ).liveData
}