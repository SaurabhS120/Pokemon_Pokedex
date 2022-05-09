package com.example.pokemon.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon.domain.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}