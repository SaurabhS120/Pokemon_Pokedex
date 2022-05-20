package com.example.pokemon.data.data_source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity

@Database(entities = [PokemonRoomEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}