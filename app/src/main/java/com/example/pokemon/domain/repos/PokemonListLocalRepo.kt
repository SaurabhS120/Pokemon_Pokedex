package com.example.pokemon.domain.repos

import androidx.paging.PagingSource
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity
import com.example.pokemon.domain.entities.PokemonListEntity

interface PokemonListLocalRepo {
    suspend fun insertAll(pokemons: List<PokemonListEntity>)
    fun pagingSource(): PagingSource<Int, PokemonRoomEntity>
    suspend fun clearAll()
    suspend fun updateImage(id: Int, image: String)
    suspend fun withTransaction(task: suspend () -> Unit)
}