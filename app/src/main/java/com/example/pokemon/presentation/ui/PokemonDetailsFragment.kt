package com.example.pokemon.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokemonDetailsBinding
import com.example.pokemon.domain.entities.PokemonEntityParcel
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {
//    companion object{
//        val POKEMON_DETAILS_KEY = "pokemon_details_object"
//    }
    val args: PokemonDetailsFragmentArgs by navArgs()
    val pokemonDetailsViewModel : PokemonDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val pokemonDetailsBinding = FragmentPokemonDetailsBinding.inflate(layoutInflater)
        val pokemonEntity : PokemonEntityParcel? = args.pokemonEntityParcel
        pokemonDetailsBinding.backButton.setOnClickListener {
            activity?.findNavController(R.id.mainFragmentContainerView)?.popBackStack()
        }
        pokemonDetailsBinding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                pokemonDetailsViewModel.gotoTab(tab?.position?:0)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
        pokemonDetailsViewModel.apply {
            pokemonName.observe(viewLifecycleOwner) {
                if (it != null && it != "") {
                    pokemonDetailsBinding.pokemonName.setText(it.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    })
                }
            }
            pokemonImagePath.observe(viewLifecycleOwner) {
                if (it != null && it != "") {
                    Glide.with(this@PokemonDetailsFragment).load(it).into(pokemonDetailsBinding.pokemonImage)
                }
            }
            pokemonDetailFragment.observe(viewLifecycleOwner) { fragment ->
//                supportFragmentManager.beginTransaction().apply {
//                    replace(pokemonDetailsBinding.detailFragmentContainer.id, fragment)
//                    commit()
//                }
            }
            loadDetails(pokemonEntity)
            return pokemonDetailsBinding.root
        }
    }
}