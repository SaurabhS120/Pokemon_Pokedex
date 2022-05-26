package com.example.pokemon.data.repoImpl.paging.mediator

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.data.data_source.local.room.mediator.PokemonRoomRemoteMediator
import com.example.pokemon.data.repoImpl.local.PokemonRoomDatabaseRepo
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.domain.repos.PokemonListPagingMediator
import com.example.pokemon.domain.usecases.PokemonBase64UseCase
import com.example.pokemon.domain.usecases.PokemonListRemoteUseCase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
@OptIn(ExperimentalPagingApi::class)
class PokemonListRoomPagingRepoImpl @Inject constructor(
    private val roomRepo: PokemonRoomDatabaseRepo,
    private val pokemonListRemoteUseCase: PokemonListRemoteUseCase,
    private val pokemonBase64Usecase: PokemonBase64UseCase
) : PokemonListPagingMediator {
    override fun getPokemons(coroutineScope: CoroutineScope): Flow<PagingData<PokemonListEntity>> {
        val pagingData = Pager(
            config = PagingConfig(pageSize = PokemonPageConfig.PAGE_SIZE),
            remoteMediator = PokemonRoomRemoteMediator(roomRepo, coroutineScope,pokemonListRemoteUseCase,pokemonBase64Usecase),
            pagingSourceFactory = { roomRepo.pagingSource() }
        ).flow
        return pagingData.map { it.map { it.toPokemonListEntity() } }
    }
}