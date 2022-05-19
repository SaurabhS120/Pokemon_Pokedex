package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.data.repoImpl.paging.mediator.PokemonListPagingRepoImpl
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    localRepo: PokemonListLocalRepo,
    remoteRepo: PokemonRemoteRepo
) : ViewModel() {
    val pokemonListPagingRepo = PokemonListPagingRepoImpl(localRepo, remoteRepo, viewModelScope)
    val pokemonList = pokemonListPagingRepo.getPokemons().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter()
}