package com.example.pokemon.presentation.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokemon.data.data_source.local.PokemonDatabaseProvider
import com.example.pokemon.data.data_source.reomote.PokemonRetrofitAPI
import com.example.pokemon.data.repoImpl.PokemonRepoImpl
import com.example.pokemon.domain.repoImpl.PokemonPagingRepoImpl
import com.example.pokemon.domain.repos.PokemonPagingRepo
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter

class PokemonListViewModel( applicationContext: Context) : ViewModel() {
    class Factory(val applicationContext: Context):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PokemonListViewModel(applicationContext) as T
        }

    }

    val database = PokemonDatabaseProvider.getDatabase(applicationContext)
    val pokemonApiInterface = PokemonRetrofitAPI.getClient
    val pokemonRepo = PokemonRepoImpl(pokemonApiInterface)
    val pokemonPagingRepo = PokemonPagingRepoImpl(database,pokemonRepo,viewModelScope)
    val pokemonList = pokemonPagingRepo.getPokemons().cachedIn(viewModelScope)
    val pokemonListRecyclerAdapter = PokemonPagingAdapter(viewModelScope,applicationContext)
}