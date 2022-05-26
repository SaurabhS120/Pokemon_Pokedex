package com.example.pokemon.domain.entities

data class PokemonDetailsEntity(
    val pokemonName:String,
    val pokemonImagePath:String,
    val pokemonAbout:PokemonAbout,
    val pokemonStats:PokemonStats
){
    companion object{
        fun createEmptyObject(): PokemonDetailsEntity {
            return PokemonDetailsEntity("","",PokemonAbout("","","",""), PokemonStats("","","","","","",""))
        }
    }
}