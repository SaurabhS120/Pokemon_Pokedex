package com.example.pokemon.data.entity

import com.example.pokemon.data.data_source.reomote.retrofit.response.PokemonDetailsResponse
import com.example.pokemon.domain.entities.PokemonAbout
import com.example.pokemon.domain.entities.PokemonStats

open class PokemonDetailsEntity(pokemonDetailsResponse: PokemonDetailsResponse) {
    val pokemonName:String
    val pokemonImagePath:String
    val pokemonAbout:PokemonAbout
    val pokemonStats:PokemonStats
    init {
        pokemonName = pokemonDetailsResponse.name?:""
        pokemonImagePath = pokemonDetailsResponse.sprites?.other?.home?.front_default ?: ""
        pokemonAbout = pokemonDetailsResponse.let{
            val species = it.species?.name ?: ""
            val height = it.height?.toString() ?: ""
            val weight = it.weight?.toString() ?: ""
            val abilities = it.abilities?.let {
                var str = ""
                it.forEachIndexed { index, abilitiesItem ->
                    str = if (index > 0) {
                        (str + " , " + abilitiesItem?.ability?.name)
                    } else {
                        (str + abilitiesItem?.ability?.name)
                    }
                }
                str
            } ?: ""
            PokemonAbout(species, height, weight, abilities)
        }
        pokemonStats = pokemonDetailsResponse.let {
            var hp = ""
            var attack = ""
            var defence = ""
            var spAttack = ""
            var spDefence = ""
            var speed = ""
            var total = ""
            it.stats?.forEach {
                when (it?.stat?.name) {
                    "hp" -> hp = it.baseStat?.toString() ?: ""
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
}