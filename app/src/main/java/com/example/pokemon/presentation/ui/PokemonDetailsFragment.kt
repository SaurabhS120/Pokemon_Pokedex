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
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.databinding.FragmentPokemonDetailsBinding
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailBaseStatsFragment
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailsAboutFragment
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokemonDetailsFragment : Fragment() {
    private val args: PokemonDetailsFragmentArgs by navArgs()
    private val pokemonDetailsViewModel: PokemonDetailsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val pokemonDetailsBinding = FragmentPokemonDetailsBinding.inflate(layoutInflater)
        pokemonDetailsBinding.backButton.setOnClickListener {
            activity?.findNavController(R.id.mainFragmentContainerView)?.popBackStack()
        }
        val tabLayout = pokemonDetailsBinding.tabs
        val viewPager = pokemonDetailsBinding.detailViewPager
        viewPager.adapter = PokemonDetailsAdapter(this)
        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            val title = when(position){
                0-> "About"
                1-> "Base Stats"
                else -> ""
            }
            tab.text = title
        }.attach()
        pokemonDetailsViewModel.apply {
            pokemonName.observe(viewLifecycleOwner) { it ->
                if (it != null && it != "") {
                    pokemonDetailsBinding.pokemonName.text = it.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    }
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
        val pokemonEntity: PokemonListEntity = args.pokemonListEntity
        pokemonDetailsViewModel.loadDetails(pokemonEntity)
    }
    class PokemonDetailsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        val pokemonDetailsAboutFragment = PokemonDetailsAboutFragment()
        val pokemonDetailsBaseStatesFragment = PokemonDetailBaseStatsFragment()
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {pokemonDetailsAboutFragment}
                1 -> {pokemonDetailsBaseStatesFragment}
                else->{pokemonDetailsAboutFragment}
            }
        }
    }
}