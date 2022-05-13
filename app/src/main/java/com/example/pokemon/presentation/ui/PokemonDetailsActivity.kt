package com.example.pokemon.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pokemon.R
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.entities.PokemonEntityParcel

class PokemonDetailsActivity : AppCompatActivity() {
    companion object{
        val POKEMON_DETAILS_KEY = "pokemon_details_object"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        val pokemonEntity : PokemonEntityParcel? = intent.getParcelableExtra<PokemonEntityParcel>(POKEMON_DETAILS_KEY)
        setTitle(pokemonEntity?.name?:"null")
    }
}