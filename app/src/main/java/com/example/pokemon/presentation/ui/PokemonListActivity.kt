package com.example.pokemon.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.pokemon.data.data_source.local.PokemonDatabase
import com.example.pokemon.data.data_source.local.PokemonDatabaseProvider
import com.example.pokemon.data.data_source.reomote.PokemonRetrofitAPI
import com.example.pokemon.data.repoImpl.PokemonRepoImpl
import com.example.pokemon.databinding.ActivityPokemonListBinding
import com.example.pokemon.domain.repoImpl.PokemonPagingRepoImpl
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

class PokemonListActivity : AppCompatActivity() {
    val pokemonListViewModel : PokemonListViewModel by viewModels{
        PokemonListViewModel.Factory(applicationContext)
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