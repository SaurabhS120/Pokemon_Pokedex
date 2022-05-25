package com.example.pokemon.domain.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity
import com.example.pokemon.domain.entities.PokemonListEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

interface PokemonListPagingMediator {
    fun getPokemons(coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob()+Dispatchers.IO)): LiveData<PagingData<PokemonListEntity>>
}