package com.example.pokemon.presentation.viewmodels

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import com.example.pokemon.presentation.ui.PokemonDetailsActivity
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailBaseStatsFragment
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailBaseStatsFragment.ArgumentHelperImpl.PokemonStats
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailsAboutFragment
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailsAboutFragment.ArgumentHelperImpl.PokemonAbout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(val remoteRepo: PokemonRemoteRepo) : ViewModel() {
    val pokemonName = MutableLiveData<String>("Pokemon name")
    val pokemonImagePath = MutableLiveData<String>("")
    val pokemonDetailFragment = MutableLiveData<Fragment>(PokemonDetailsAboutFragment().apply {
        arguments = PokemonDetailsAboutFragment.ArgumentHelperImpl.createBundle(
            PokemonAbout(
                "",
                "",
                "",
                ""
            )
        )
    })

    companion object {
        object TABS {
            val ABOUT = 0
            val BASE_STATS = 1
            val EVOLUTION = 2
            val MOVES = 3
        }
    }

    inner abstract class Arguments(val bundleObj: Bundle) {
        fun getBundle(): Bundle = bundleObj
    }

    //pokemon about
    inner class PokemonAboutArguments(pokemonAbout: PokemonAbout) :
        Arguments(PokemonDetailsAboutFragment.createBundle(pokemonAbout))

    var pokemonAbout = PokemonAbout("", "", "", "")
        set(pokemonAbout) {
            pokemonAboutArguments = PokemonAboutArguments((pokemonAbout))
            field = pokemonAbout
        }
    var pokemonAboutArguments = PokemonAboutArguments(pokemonAbout)

    //base stats
    inner class PokemonBaseStatsArguments(pokemonBaseStats: PokemonStats) :
        Arguments(PokemonDetailBaseStatsFragment.createBundle(pokemonBaseStats))

    var pokemonBaseStats = PokemonStats("", "", "", "", "", "", "")
        set(pokemonBaseStats) {
            pokemonBaseStatsArguments = PokemonBaseStatsArguments(pokemonBaseStats)
            field = pokemonBaseStats
        }
    var pokemonBaseStatsArguments = PokemonBaseStatsArguments(pokemonBaseStats)

    var pokemonDetails: PokemonDetailsResponse? = null
        set(pokemonDetails: PokemonDetailsResponse?) {
            pokemonDetails?.let {
                pokemonName.postValue(pokemonDetails.name ?: "")
                pokemonImagePath.postValue(pokemonDetails.sprites?.other?.home?.front_default ?: "")

                pokemonAbout = with(pokemonDetails) {
                    val species = species?.name ?: ""
                    val height = height?.toString() ?: ""
                    val weight = weight?.toString() ?: ""
                    val abilities = abilities?.let {
                        var str = ""
                        it.forEachIndexed { index, abilitiesItem ->
                            if (index > 0) {
                                str = str + " , " + abilitiesItem?.ability?.name ?: ""
                            } else {
                                str = str + abilitiesItem?.ability?.name ?: ""
                            }
                        }
                        str
                    } ?: ""
                    PokemonAbout(species, height, weight, abilities)
                }
                pokemonBaseStats = with(pokemonDetails) {

                    var hp = ""
                    var attack = ""
                    var defence = ""
                    var spAttack = ""
                    var spDefence = ""
                    var speed = ""
                    var total = ""
                    pokemonDetails.stats?.forEach {
                        when (it?.stat?.name) {
                            "hp" -> hp = it?.baseStat?.toString() ?: ""
                            "attack" -> attack = it.baseStat.toString()
                            "defense" -> defence = it.baseStat.toString()
                            "special-attack" -> spAttack = it.baseStat.toString()
                            "special-defense" -> spDefence = it.baseStat.toString()
                            "speed" -> speed = it.baseStat.toString()
                            "total" -> total = it.baseStat.toString()
                        }
                    }
                    PokemonStats(hp, attack, defence, spAttack, spDefence, speed, total)
                }
            }
            field = null
        }

    fun loadDetails(pokemonEntity: PokemonEntity?) {
        pokemonEntity?.let {
            viewModelScope.launch(Dispatchers.IO) {
                pokemonDetails = remoteRepo.getPokemonDetails(pokemonEntity.id ?: 0)
                gotoTab(TABS.ABOUT)
            }

        }
    }

    fun gotoTab(tabNo: Int) {
        when (tabNo) {
            TABS.ABOUT -> {
                val fragment = PokemonDetailsAboutFragment()
                fragment.arguments = pokemonAboutArguments.getBundle()
                pokemonDetailFragment.postValue(fragment)
            }
            TABS.BASE_STATS -> {

                val fragment = PokemonDetailBaseStatsFragment()
                fragment.arguments = pokemonBaseStatsArguments.getBundle()
                pokemonDetailFragment.postValue(fragment)
            }
        }
    }
}