package com.example.pokemon.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokemonListBinding
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.presentation.ui.recycler_view.PokemonListRecyclerView
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    val pokemonListViewModel: PokemonListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        super.onCreate(savedInstanceState)
        val pokemonListBinding = FragmentPokemonListBinding.inflate(layoutInflater)
        val pokemonListRecyclerView = PokemonListRecyclerView(
            pokemonListBinding.pokemonListRecyclerView,
            this
        )
        pokemonListRecyclerView.setOnclickListener { pokemonListEntityTemp: PokemonListEntity? ->
            val pokemonId = pokemonListEntityTemp?.id?:1
            val action =
                PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(pokemonId)
            activity?.findNavController(R.id.mainFragmentContainerView)?.navigate(action)
        }
        return pokemonListBinding.root
    }
}