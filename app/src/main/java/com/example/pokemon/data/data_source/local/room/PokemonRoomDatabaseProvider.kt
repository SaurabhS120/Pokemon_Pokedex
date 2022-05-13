package com.example.pokemon.data.data_source.local.room

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object PokemonRoomDatabaseProvider {
    fun getDatabase(applicationContext:Context): PokemonDatabase {
        return Room.databaseBuilder(
            applicationContext,
            PokemonDatabase::class.java,
            "pokemon_database"
        ).build()
    }
}