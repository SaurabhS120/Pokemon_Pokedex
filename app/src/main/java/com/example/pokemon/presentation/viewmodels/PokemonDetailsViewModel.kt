package com.example.pokemon.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(val remoteRepo: PokemonRemoteRepo): ViewModel() {
    val pokemonName = MutableLiveData<String>("Pokemon name")
    val pokemonImagePath = MutableLiveData<String>("")
    fun loadDetails(pokemonEntity: PokemonEntity?){
        pokemonEntity?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val pokemonDetails = remoteRepo.getPokemonDetails(pokemonEntity.id ?: 0)
                pokemonName.postValue(pokemonDetails.name ?: "")
                pokemonImagePath.postValue(pokemonDetails.sprites?.other?.home?.front_default ?: "")
            }
        }
    }
}