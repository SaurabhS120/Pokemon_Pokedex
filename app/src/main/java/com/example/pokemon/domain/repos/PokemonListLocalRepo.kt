package com.example.pokemon.domain.repos

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.domain.entities.PokemonEntity

interface PokemonListLocalRepo {
    suspend fun insertAll(pokemons:List<PokemonEntity>)
    fun pagingSource(): PagingSource<Int, PokemonEntity>
    suspend fun clearAll()
    suspend fun updateImage(id:Int,image:String)
    suspend fun withTransaction(task:suspend ()->Unit)
}