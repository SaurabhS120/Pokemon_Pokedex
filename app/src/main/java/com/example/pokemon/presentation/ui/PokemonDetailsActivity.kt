package com.example.pokemon.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.databinding.ActivityPokemonDetailsBinding
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.entities.PokemonEntityParcel
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailsAboutFragment
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokemonDetailsActivity : AppCompatActivity() {
    companion object{
        val POKEMON_DETAILS_KEY = "pokemon_details_object"
    }
    val pokemonDetailsViewModel : PokemonDetailsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pokemonDetailsBinding = ActivityPokemonDetailsBinding.inflate(layoutInflater)
        setContentView(pokemonDetailsBinding.root)
        val pokemonEntity : PokemonEntityParcel? = intent.getParcelableExtra<PokemonEntityParcel>(POKEMON_DETAILS_KEY)
        supportActionBar?.hide()
        pokemonDetailsBinding.backButton.setOnClickListener {
            finish()
        }
        pokemonDetailsBinding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pokemonDetailsViewModel.gotoTab(tab?.position?:0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        pokemonDetailsViewModel.apply {
            pokemonName.observe(this@PokemonDetailsActivity) {
                if (it != null && it != "") {
                    pokemonDetailsBinding.pokemonName.setText(it.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    })
                }
            }
            pokemonImagePath.observe(this@PokemonDetailsActivity) {
                if (it != null && it != "") {
                    Glide.with(this@PokemonDetailsActivity).load(it).into(pokemonDetailsBinding.pokemonImage)
                }
            }
            pokemonDetailFragment.observe(this@PokemonDetailsActivity) { fragment ->
                supportFragmentManager.beginTransaction().apply {
                    replace(pokemonDetailsBinding.detailFragmentContainer.id, fragment)
                    commit()
                }
            }
            loadDetails(pokemonEntity)
        }
    }
}