package com.example.pokemon.data.data_source.local.room

import android.content.Context
import androidx.room.Room

object PokemonRoomDatabaseProvider {
    fun getDatabase(applicationContext:Context): PokemonDatabase {
        return Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }
}