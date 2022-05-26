package com.example.pokemon.presentation.ui.recycler_view

import androidx.lifecycle.viewModelScope
import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.presentation.ui.PokemonListFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonListRecyclerView(
    pokemonListRecyclerView: RecyclerView,
    pokemonListFragment: PokemonListFragment
) {

    private val pokemonListViewModel = pokemonListFragment.pokemonListViewModel
    private val adapter = pokemonListViewModel.pokemonListRecyclerAdapter

    init {
        pokemonListRecyclerView.layoutManager = GridLayoutManager(pokemonListFragment.context, 2)
        pokemonListRecyclerView.adapter = adapter
        pokemonListViewModel.viewModelScope.launch(Dispatchers.Default){
            pokemonListViewModel.pokemonList.collect {
                adapter.submitData(
                    pokemonListFragment.lifecycle,
                    it
                )
            }
        }

    }

    fun setOnclickListener(listener: (pokemonListEntity: PokemonListEntity?) -> Unit) {
        adapter.setOnClickListener(listener)
    }
}