package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.domain.usecases.PokemonListLocalPagingUseCase
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    pokemonListLocalPagingUseCase: PokemonListLocalPagingUseCase
) : ViewModel() {
    val pokemonList = pokemonListLocalPagingUseCase.invoke().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter()
}