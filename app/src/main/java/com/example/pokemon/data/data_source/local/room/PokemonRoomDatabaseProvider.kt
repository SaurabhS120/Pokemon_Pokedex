package com.example.pokemon.data.data_source.local.room

import android.content.Context
import androidx.room.Room

object PokemonRoomDatabaseProvider {
    private var database: PokemonDatabase? = null
    fun getDatabase(applicationContext: Context): PokemonDatabase {
        return database ?: Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build().apply {
            database = this
        }
    }
}