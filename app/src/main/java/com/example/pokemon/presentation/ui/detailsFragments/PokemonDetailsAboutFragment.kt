package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemon.databinding.FragmentPokemonDetailsAboutBinding
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsAboutFragment : Fragment() {
    private lateinit var pokemonDetailsAboutFragmentBinding : FragmentPokemonDetailsAboutBinding
    val pokemonDetailsViewModel : PokemonDetailsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pokemonDetailsAboutFragmentBinding = FragmentPokemonDetailsAboutBinding.inflate(layoutInflater)
        pokemonDetailsViewModel.pokemonAbout.observe(viewLifecycleOwner){
            pokemonDetailsAboutFragmentBinding.apply {
                speciesTextView.setText(it.species)
                weightTextView.setText(it.weight)
                heightTextView.setText(it.height)
                abilitiesTextView.setText(it.abilities)
            }
        }
        return pokemonDetailsAboutFragmentBinding.root
    }
}