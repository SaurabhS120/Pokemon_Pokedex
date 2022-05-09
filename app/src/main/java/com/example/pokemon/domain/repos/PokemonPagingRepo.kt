package com.example.pokemon.domain.repos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.pokemon.domain.entities.PokemonEntity

interface PokemonPagingRepo {
    fun getPokemons(): LiveData<PagingData<PokemonEntity>>
}