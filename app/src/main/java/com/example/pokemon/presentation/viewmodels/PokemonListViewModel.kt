package com.example.pokemon.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.data.data_source.local.PokemonLocalRepo
import com.example.pokemon.data.data_source.reomote.PokemonRemoteRepo
import com.example.pokemon.domain.repoImpl.PokemonPagingRepoImpl
import com.example.pokemon.domain.repos.PokemonListRemoteRepo
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    localRepo: PokemonLocalRepo,
    remoteRepo: PokemonRemoteRepo
) : ViewModel() {
    val pokemonPagingRepo = PokemonPagingRepoImpl(localRepo,remoteRepo,viewModelScope)
    val pokemonList = pokemonPagingRepo.getPokemons().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter()
}