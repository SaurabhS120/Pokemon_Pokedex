package com.example.pokemon.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ActivityPokemonListBinding
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.entities.PokemonEntityParcel
import com.example.pokemon.presentation.ui.recycler_view.PokemonListRecyclerView
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
        val pokemonListRecyclerView = PokemonListRecyclerView(
            pokemonListActivityBinding.pokemonListRecyclerView,
            this
        )
        pokemonListRecyclerView.setOnclickListener { pokemonEntity:PokemonEntity?->
            val intent = Intent(this,PokemonDetailsActivity::class.java)
            val pokemonEntityParcel = pokemonEntity?.let {
                val parcel = PokemonEntityParcel(it)
                intent.putExtra(PokemonDetailsActivity.POKEMON_DETAILS_KEY,parcel)
                startActivity(intent)
            }
        }

    }
}