package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemon.databinding.FragmentPokemonDetailBaseStatsBinding
import com.example.pokemon.presentation.viewmodels.PokemonDetailsViewModel

class PokemonDetailBaseStatsFragment : Fragment() {
    lateinit var baseStatsBinding: FragmentPokemonDetailBaseStatsBinding
    val pokemonDetailsViewModel: PokemonDetailsViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseStatsBinding = FragmentPokemonDetailBaseStatsBinding.inflate(layoutInflater)
        pokemonDetailsViewModel.baseStats.observe(viewLifecycleOwner) {
            baseStatsBinding.apply {
                hpTextView.text = it.hp
                hpPreogress.progress = it.hp.toInt()
                attackTextView.text = it.attack
                attackProgress.progress = it.attack.toInt()
                defenceTextView.text = it.defence
                defenceProgress.progress = it.defence.toInt()
                spAttackTextView.text = it.spAttack
                spAttackProgress.progress = it.spAttack.toInt()
                spDefenceTextView.text = it.spDefence
                spDefenceProgress.progress = it.spDefence.toInt()
                speedTextView.text = it.speed
                speedProgress.progress = it.speed.toInt()
                totalTextView.text = it.total
                if (it.total.length > 0) totalProgress.progress = it.total.toInt()
            }
        }
        return baseStatsBinding.root
    }
}