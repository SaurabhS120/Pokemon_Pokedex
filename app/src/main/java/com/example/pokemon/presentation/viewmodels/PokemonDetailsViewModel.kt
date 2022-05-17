package com.example.pokemon.presentation.viewmodels

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import com.example.pokemon.presentation.ui.detailsFragments.PokemonDetailsAboutFragment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(val remoteRepo: PokemonRemoteRepo): ViewModel() {
    val pokemonName = MutableLiveData<String>("Pokemon name")
    val pokemonImagePath = MutableLiveData<String>("")
    val pokemonDetailFragment = MutableLiveData<Fragment>(PokemonDetailsAboutFragment().apply {
        arguments = PokemonDetailsAboutFragment.ArgumentHelper.createBundle("","","","")
    })
    fun loadDetails(pokemonEntity: PokemonEntity?){
        pokemonEntity?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val pokemonDetails = remoteRepo.getPokemonDetails(pokemonEntity.id ?: 0)
                pokemonName.postValue(pokemonDetails.name ?: "")
                pokemonImagePath.postValue(pokemonDetails.sprites?.other?.home?.front_default ?: "")

                val species = pokemonDetails.species?.name?:""
                val height = pokemonDetails.height?.toString()?:""
                val weight = pokemonDetails.weight?.toString()?:""
                val abilities = pokemonDetails.abilities?.let {
                    var str = ""
                    it.forEachIndexed { index, abilitiesItem ->
                        if (index>0){
                            str = str + " , " +abilitiesItem?.ability?.name?:""
                        }else{
                            str = str +abilitiesItem?.ability?.name?:""
                        }
                    }
                    str
                }?:""
                pokemonDetailFragment.postValue(PokemonDetailsAboutFragment().apply {
                    arguments= PokemonDetailsAboutFragment.createBundle(species,height,weight,abilities)
                })
            }

        }
    }
}