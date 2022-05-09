package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.domain.repos.PokemonPagingRepo
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter

class PokemonListViewModel(pokenonPagingRepo: PokemonPagingRepo) : ViewModel() {
    class Factory(val pokenonPagingRepo: PokemonPagingRepo):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PokemonListViewModel(pokenonPagingRepo) as T
        }

    }
    val pokemonList = pokenonPagingRepo.getPokemons().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter(viewModelScope)
}