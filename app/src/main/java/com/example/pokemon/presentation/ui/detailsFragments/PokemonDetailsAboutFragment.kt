package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokemonDetailsAboutBinding
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonDetailsAboutFragment : Fragment(),PokemonDetailFragment {
    companion object ArgumentHelper{
        val SPECIES = "species"
        val HEIGHT = "height"
        val WEIGHT = "weight"
        val ABILITIES = "abilities"
        fun createBundle(species:String,height:String,weight:String,abilities:String):Bundle{
            val bundle = Bundle()
            bundle.putString(SPECIES,species)
            bundle.putString(HEIGHT,height)
            bundle.putString(WEIGHT,weight)
            bundle.putString(ABILITIES,abilities)
            return bundle
        }
    }
    private lateinit var pokemonDetailsAboutFragmentBinding : FragmentPokemonDetailsAboutBinding
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
        pokemonDetailsAboutFragmentBinding.speciesTextView.setText(getSpecies())
        pokemonDetailsAboutFragmentBinding.weightTextView.setText(getWeight())
        pokemonDetailsAboutFragmentBinding.heightTextView.setText(getHeight())
        pokemonDetailsAboutFragmentBinding.abilitiesTextView.setText(getAbilities())
        return pokemonDetailsAboutFragmentBinding.root
    }

}