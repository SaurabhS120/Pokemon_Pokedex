package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemon.databinding.FragmentPokemonDetailBaseStatsBinding
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailFragment.ArgumentHelper

class PokemonDetailBaseStatsFragment : Fragment(),PokemonDetailFragment<PokemonDetailBaseStatsFragment.ArgumentHelperImpl.PokemonStats> {
    companion object ArgumentHelperImpl: ArgumentHelper<ArgumentHelperImpl.PokemonStats> {
        data class PokemonStats(val hp:String,val attack:String,val defence:String,val spAttack:String,val spDefence:String,val speed:String,val total:String)
        val HP_KEY="hp"
        val ATTACK_KEY="attack"
        val DEFENCE_KEY="defence"
        val SP_ATTACK_KEY="spAttack"
        val SP_DEFENCE_KEY="spDefence"
        val SPEED_KEY="speed"
        val TOTAL_KEY="total"
        override fun createBundle(stats: PokemonStats)=Bundle().apply{
            putString(HP_KEY,stats.hp)
            putString(ATTACK_KEY,stats.hp)
            putString(DEFENCE_KEY,stats.defence)
            putString(SP_ATTACK_KEY,stats.spAttack)
            putString(SP_DEFENCE_KEY,stats.spDefence)
            putString(SPEED_KEY,stats.speed)
            putString(TOTAL_KEY,stats.total)
        }

    }
    lateinit var baseStatsBinding: FragmentPokemonDetailBaseStatsBinding
    val hp get()=arguments?.getString(HP_KEY)?:""
    val attack get()=arguments?.getString(ATTACK_KEY)?:""
    val defence get()=arguments?.getString(DEFENCE_KEY)?:""
    val spAttack get()=arguments?.getString(SP_ATTACK_KEY)?:""
    val spDefence get()=arguments?.getString(SP_DEFENCE_KEY)?:""
    val speed get()=arguments?.getString(SPEED_KEY)?:""
    val total get()=arguments?.getString(TOTAL_KEY)?:""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseStatsBinding = FragmentPokemonDetailBaseStatsBinding.inflate(layoutInflater)
        baseStatsBinding.apply {
            hpTextView.text = hp
            attackTextView.text = attack
            defenceTextView.text = defence
            spAttackTextView.text = spAttack
            spDefenceTextView.text = spDefence
            speedTextView.text = speed
            totalTextView.text = total
        }
        return baseStatsBinding.root
    }

    override fun argumentHelper(): ArgumentHelper<PokemonStats> =  ArgumentHelperImpl

}