package com.example.pokemon.presentation.ui.recycler_view

import androidx.paging.map
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.presentation.ui.PokemonListFragment

class PokemonListRecyclerView(
    pokemonListRecyclerView: RecyclerView,
    pokemonListFragment: PokemonListFragment
) {

    private val pokemonListViewModel = pokemonListFragment.pokemonListViewModel
    private val adapter = pokemonListViewModel.pokemonListRecyclerAdapter

    init {
        pokemonListRecyclerView.layoutManager = GridLayoutManager(pokemonListFragment.context, 2)
        pokemonListRecyclerView.adapter = adapter
        pokemonListViewModel.pokemonList.observe(pokemonListFragment) {
            adapter.submitData(
                pokemonListFragment.lifecycle,
                it
            )
        }

    }

    fun setOnclickListener(listener: (pokemonListEntity: PokemonListEntity?) -> Unit) {
        adapter.setOnClickListener(listener)
    }
}