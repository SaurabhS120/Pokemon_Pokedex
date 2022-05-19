package com.example.pokemon.presentation.ui.recycler_view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.presentation.ui.PokemonListFragment

class PokemonListRecyclerView(
    pokemonListRecyclerView: RecyclerView,
    pokemonListFragment: PokemonListFragment
) {

    val pokemonListViewModel = pokemonListFragment.pokemonListViewModel
    val adapter = pokemonListViewModel.pokemonListRecyclerAdapter

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

    fun setOnclickListener(listener: (pokemonEntity: PokemonEntity?) -> Unit) {
        adapter.setOnClickListener(listener)
    }
}