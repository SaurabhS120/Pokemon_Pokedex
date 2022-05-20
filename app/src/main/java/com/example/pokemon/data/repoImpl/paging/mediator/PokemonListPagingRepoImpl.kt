package com.example.pokemon.data.repoImpl.paging.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.data.data_source.mediator.PokemonRemoteMediator
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.repos.PokemonListPagingRepo
import com.example.pokemon.domain.usecases.PokemonBase64UseCase
import com.example.pokemon.domain.usecases.PokemonListRemoteUseCase
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagingApi::class)
class PokemonListPagingRepoImpl(
    private val localRepo: PokemonListLocalRepo,
    private val viewModelScope: CoroutineScope,
    private val pokemonListRemoteUseCase: PokemonListRemoteUseCase,
    private val pokemonBase64Usecase: PokemonBase64UseCase
) : PokemonListPagingRepo {
    override fun getPokemons() = Pager(
        config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
        remoteMediator = PokemonRemoteMediator(localRepo, viewModelScope,pokemonListRemoteUseCase,pokemonBase64Usecase),
        pagingSourceFactory = { localRepo.pagingSource() }
    ).liveData
}