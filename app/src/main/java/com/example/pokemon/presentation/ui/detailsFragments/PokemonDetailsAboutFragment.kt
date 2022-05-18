package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemon.databinding.FragmentPokemonDetailsAboutBinding
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailFragment.ArgumentHelper
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailsAboutFragment.ArgumentHelperImpl.PokemonAbout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsAboutFragment : Fragment(),PokemonDetailFragment<PokemonAbout> {
    companion object ArgumentHelperImpl: ArgumentHelper<PokemonAbout>{
        val SPECIES = "species"
        val HEIGHT = "height"
        val WEIGHT = "weight"
        val ABILITIES = "abilities"
        data class PokemonAbout(val species:String, val height:String,val weight:String,val abilities:String)
        override fun createBundle(about:PokemonAbout)=Bundle().apply{
            putString(SPECIES,about.species)
            putString(HEIGHT,about.height)
            putString(WEIGHT,about.weight)
            putString(ABILITIES,about.abilities)
        }
    }
    private lateinit var pokemonDetailsAboutFragmentBinding : FragmentPokemonDetailsAboutBinding
    override fun argumentHelper(): ArgumentHelper<PokemonAbout>  = ArgumentHelperImpl
    fun getSpecies() = arguments?.getString(SPECIES)?:""
    fun getHeight() = arguments?.getString(HEIGHT)?:""
    fun getWeight() = arguments?.getString(WEIGHT)?:""
    fun getAbilities() = arguments?.getString(ABILITIES)?:""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        pokemonDetailsAboutFragmentBinding = FragmentPokemonDetailsAboutBinding.inflate(layoutInflater)
        pokemonDetailsAboutFragmentBinding.apply {
            speciesTextView.setText(getSpecies())
            weightTextView.setText(getWeight())
            heightTextView.setText(getHeight())
            abilitiesTextView.setText(getAbilities())
        }
        return pokemonDetailsAboutFragmentBinding.root
    }


}