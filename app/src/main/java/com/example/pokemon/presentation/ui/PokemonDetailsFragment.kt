package com.example.pokemon.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
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
    val args: PokemonDetailsFragmentArgs by navArgs()
    val pokemonDetailsViewModel: PokemonDetailsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val pokemonDetailsBinding = FragmentPokemonDetailsBinding.inflate(layoutInflater)
        pokemonDetailsBinding.backButton.setOnClickListener {
            activity?.findNavController(R.id.mainFragmentContainerView)?.popBackStack()
        }
        pokemonDetailsBinding.tabs.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabName = tab?.text.toString() ?: ""
                when (tabName) {
                    getString(R.string.about) -> {
                        pokemonDetailsBinding.detailFragmentContainer.findNavController()
                            .navigate(R.id.pokemonDetailsAboutAction)
                    }
                    getString(R.string.base_stats) -> {
                        pokemonDetailsBinding.detailFragmentContainer.findNavController()
                            .navigate(R.id.pokemonDetailBaseStatsAction)
                    }

                }
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
                    Glide.with(this@PokemonDetailsFragment).load(it)
                        .into(pokemonDetailsBinding.pokemonImage)
                }
            }
            return pokemonDetailsBinding.root
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pokemonDetailsViewModel.reset()
        val pokemonEntity: PokemonEntityParcel? = args.pokemonEntityParcel
        pokemonDetailsViewModel.loadDetails(pokemonEntity)
    }
}