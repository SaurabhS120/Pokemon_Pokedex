package com.example.pokemon.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
open class PokemonEntity(
    @PrimaryKey
    var id:Int?=null,
    val name : String,
    val url:String,
    var imageBase64:String="")