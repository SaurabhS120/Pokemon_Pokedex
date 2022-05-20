package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import com.example.pokemon.domain.usecases.PokemonBase64UseCase
import com.example.pokemon.domain.usecases.PokemonListLocalPagingUseCase
import com.example.pokemon.domain.usecases.PokemonListRemoteUseCase
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    localRepo: PokemonListLocalRepo,
    remoteRepo: PokemonRemoteRepo,
    pagingUseCase: PokemonListLocalPagingUseCase
) : ViewModel() {
    private val pokemonListPagingRepo = pagingUseCase.call(viewModelScope)
    val pokemonList = pokemonListPagingRepo.getPokemons().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter()
}