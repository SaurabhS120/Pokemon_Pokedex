package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.entities.PokemonAbout
import com.example.pokemon.domain.entities.PokemonDetailsEntity
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.domain.entities.PokemonStats
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(private val remoteRepo: PokemonRemoteRepo) :
    ViewModel() {
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

    private var pokemonDetails: PokemonDetailsEntity? = null
        set(pokemonDetails) {
            pokemonDetails?.let { it ->
                pokemonName.postValue(it.pokemonName ?: "")
                pokemonImagePath.postValue(it.pokemonImagePath)
                pokemonAbout.postValue(it.pokemonAbout)
                baseStats.postValue(it.pokemonStats)
                field = it
                return
            }
            field = null
        }

    fun loadDetails(pokemonListEntity: PokemonListEntity?) {
        pokemonListEntity?.let {
            viewModelScope.launch(Dispatchers.IO) {
                pokemonDetails = remoteRepo.getPokemonDetails(pokemonListEntity.id ?: 0)
            }

        }
    }
}