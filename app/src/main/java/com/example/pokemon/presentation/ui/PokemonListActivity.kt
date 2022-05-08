package com.example.pokemon.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.databinding.ActivityPokemonListBinding
import com.example.pokemon.domain.repoImpl.PokemonPagingRepoImpl
import com.example.pokemon.presentation.recycler_adapter.PokemonPagingAdapter
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import kotlinx.coroutines.coroutineScope

class PokemonListActivity : AppCompatActivity() {
    val pokemonListViewModel : PokemonListViewModel by viewModels{
        val pokemonPagingRepo = PokemonPagingRepoImpl()
        PokemonListViewModel.Factory(pokemonPagingRepo)
    }
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