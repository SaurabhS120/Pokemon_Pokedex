package com.example.pokemon.data.data_source.local

import android.content.Context
import androidx.room.Room

object PokemonDatabaseProvider {
    private var database: PokemonDatabase?=null
    private fun initializeDatabase(applicationContext: Context)= Room.databaseBuilder(
        applicationContext,
        PokemonDatabase::class.java,
        "pokemon_database"
    ).build()

    fun getDatabase(applicationContext:Context): PokemonDatabase = database?: initializeDatabase(applicationContext)
}