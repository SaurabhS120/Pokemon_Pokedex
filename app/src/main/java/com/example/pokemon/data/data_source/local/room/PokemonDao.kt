package com.example.pokemon.data.data_source.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.domain.entities.PokemonEntity

@Dao
interface PokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons")
    fun pagingSource(): PagingSource<Int, PokemonEntity>

    @Query("DELETE FROM pokemons")
    suspend fun clearAll()

    @Query("UPDATE pokemons SET imageBase64=:image where id=:id")
    suspend fun updateImage(id: Int, image: String)
}