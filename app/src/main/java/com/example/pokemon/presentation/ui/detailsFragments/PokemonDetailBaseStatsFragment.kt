package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemon.databinding.FragmentPokemonDetailBaseStatsBinding
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel

class PokemonDetailBaseStatsFragment : Fragment(){
    lateinit var baseStatsBinding: FragmentPokemonDetailBaseStatsBinding
    val pokemonDetailsViewModel : PokemonDetailsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseStatsBinding = FragmentPokemonDetailBaseStatsBinding.inflate(layoutInflater)
        pokemonDetailsViewModel.baseStats.observe(viewLifecycleOwner){
            baseStatsBinding.apply {
                hpTextView.text = it.hp
                attackTextView.text = it.attack
                defenceTextView.text = it.defence
                spAttackTextView.text = it.spAttack
                spDefenceTextView.text = it.spDefence
                speedTextView.text = it.speed
                totalTextView.text = it.total
            }
        }
        return baseStatsBinding.root
    }
}