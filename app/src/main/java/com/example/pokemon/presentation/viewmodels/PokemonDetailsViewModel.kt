package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.model.PokemonDetailsResponse
import com.example.pokemon.domain.entities.PokemonAbout
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.entities.PokemonStats
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(val remoteRepo: PokemonRemoteRepo) : ViewModel() {
    val pokemonName = MutableLiveData("Pokemon name")
    val pokemonImagePath = MutableLiveData("")
    val pokemonAbout = MutableLiveData(PokemonAbout("", "", "", ""))
    val baseStats = MutableLiveData(PokemonStats("", "", "", "", "", "", ""))
    fun reset() {
        pokemonName.value = "Pokemon name"
        pokemonImagePath.value = ""
        pokemonAbout.value = PokemonAbout("", "", "", "")
        baseStats.value = PokemonStats("", "", "", "", "", "", "")
    }

    var pokemonDetails: PokemonDetailsResponse? = null
        set(pokemonDetails: PokemonDetailsResponse?) {
            pokemonDetails?.let {
                pokemonName.postValue(pokemonDetails.name ?: "")
                pokemonImagePath.postValue(pokemonDetails.sprites?.other?.home?.front_default ?: "")

                pokemonAbout.postValue(with(pokemonDetails) {
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
                })
                baseStats.postValue(with(pokemonDetails) {

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
                })
                field = it
                return
            }
            field = null
        }

    fun loadDetails(pokemonEntity: PokemonEntity?) {
        pokemonEntity?.let {
            viewModelScope.launch(Dispatchers.IO) {
                pokemonDetails = remoteRepo.getPokemonDetails(pokemonEntity.id ?: 0)
            }

        }
    }
}