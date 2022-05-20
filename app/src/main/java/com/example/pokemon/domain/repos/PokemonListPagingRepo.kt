package com.example.pokemon.domain.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity

interface PokemonListPagingRepo {
    fun getPokemons(): LiveData<PagingData<PokemonRoomEntity>>
}