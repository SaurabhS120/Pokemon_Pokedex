package com.example.pokemon.data.data_source.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pokemon.data.entity.PokemonListEntity

@Entity(tableName = "pokemons")
open class PokemonRoomEntity(
    @PrimaryKey
    var id: Int? = null,
    val name: String,
    val url: String,
    var imageBase64: String? = ""
){
    fun toPokemonListEntity() = PokemonListEntity(id, name, url, imageBase64)
}