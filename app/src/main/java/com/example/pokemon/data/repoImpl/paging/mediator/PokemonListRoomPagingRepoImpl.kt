package com.example.pokemon.data.repoImpl.paging.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.data.data_source.mediator.PokemonRemoteMediator
import com.example.pokemon.data.repoImpl.local.PokemonRoomDatabaseRepo
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.domain.repos.PokemonListPagingMediator
import com.example.pokemon.domain.usecases.PokemonBase64UseCase
import com.example.pokemon.domain.usecases.PokemonListRemoteUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalPagingApi::class)
class PokemonListRoomPagingRepoImpl @Inject constructor(
    private val roomRepo: PokemonRoomDatabaseRepo,
    private val pokemonListRemoteUseCase: PokemonListRemoteUseCase,
    private val pokemonBase64Usecase: PokemonBase64UseCase
) : PokemonListPagingMediator {
    override fun getPokemons(coroutineScope: CoroutineScope): LiveData<PagingData<PokemonListEntity>> {
        val pagingData = Pager(
            config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
            remoteMediator = PokemonRemoteMediator(roomRepo, coroutineScope,pokemonListRemoteUseCase,pokemonBase64Usecase),
            pagingSourceFactory = { roomRepo.pagingSource() }
        ).liveData
        return pagingData.map { it.map { it.toPokemonListEntity() } }
    }
}