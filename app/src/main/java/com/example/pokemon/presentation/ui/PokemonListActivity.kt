package com.example.pokemon.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.databinding.ActivityPokemonListBinding
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListActivity : AppCompatActivity() {
    val pokemonListViewModel : PokemonListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemonListActivityBinding = ActivityPokemonListBinding.inflate(layoutInflater)
        setContentView(pokemonListActivityBinding.root)
        supportActionBar?.hide()
        val pokemonListRecyclerView = pokemonListActivityBinding.pokemonListRecyclerView
        pokemonListRecyclerView.layoutManager = GridLayoutManager(this,2)
        pokemonListRecyclerView.adapter = pokemonListViewModel.pokemonListRecyclerAdapter
        pokemonListViewModel.pokemonList.observe(this){
            pokemonListViewModel.pokemonListRecyclerAdapter.submitData(lifecycle,it)
        }
    }
}