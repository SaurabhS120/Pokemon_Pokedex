package com.example.pokemon.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentPokemonListBinding
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.entities.PokemonEntityParcel
import com.example.pokemon.presentation.ui.recycler_view.PokemonListRecyclerView
import com.example.pokemon.presentation.viewmodels.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokemonListFragment : Fragment() {
    val pokemonListViewModel : PokemonListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        super.onCreate(savedInstanceState)
        val pokemonListBinding = FragmentPokemonListBinding.inflate(layoutInflater)
        val pokemonListRecyclerView = PokemonListRecyclerView(
            pokemonListBinding.pokemonListRecyclerView,
            this
        )
        pokemonListRecyclerView.setOnclickListener { pokemonEntity:PokemonEntity?->
            val pokemonEntityParcel = PokemonEntityParcel(pokemonEntity?:PokemonEntity(-1,"","",""))
            val action = PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetailsFragment(pokemonEntityParcel)
            activity?.findNavController(R.id.mainFragmentContainerView)?.navigate(action)
//            val intent = Intent(this,PokemonDetailsFragment::class.java)
//            val pokemonEntityParcel = pokemonEntity?.let {
//                val parcel = PokemonEntityParcel(it)
//                intent.putExtra(PokemonDetailsFragment.POKEMON_DETAILS_KEY,parcel)
//                startActivity(intent)
//            }
        }
        return pokemonListBinding.root
    }
}