package com.example.pokemon.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.data.data_source.local.PokemonDatabase
import com.example.pokemon.domain.repoImpl.PokemonPagingRepoImpl
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    @ApplicationContext applicationContext: Context,
    database : PokemonDatabase,
    pokemonRemoteRepo:PokemonRemoteRepo
) : ViewModel() {
    val pokemonPagingRepo = PokemonPagingRepoImpl(database,pokemonRemoteRepo,viewModelScope)
    val pokemonList = pokemonPagingRepo.getPokemons().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter(viewModelScope,applicationContext)
}