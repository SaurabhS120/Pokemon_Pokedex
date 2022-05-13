package com.example.pokemon.presentation.ui.recycler_view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.presentation.ui.PokemonListActivity

class PokemonListRecyclerView(
    pokemonListRecyclerView: RecyclerView,
    pokemonListActivity: PokemonListActivity
) {

    val pokemonListViewModel = pokemonListActivity.pokemonListViewModel
    val adapter = pokemonListViewModel.pokemonListRecyclerAdapter
    init {
        pokemonListRecyclerView.layoutManager = GridLayoutManager(pokemonListActivity, 2)
        pokemonListRecyclerView.adapter = adapter
        pokemonListViewModel.pokemonList.observe(pokemonListActivity) {
            adapter.submitData(
                pokemonListActivity.lifecycle,
                it
            )
        }

    }
    fun setOnclickListener(listener : (pokemonEntity: PokemonEntity?)->Unit){
        adapter.setOnClickListener(listener)
    }
}