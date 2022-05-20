package com.example.pokemon.data.repoImpl.local

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.example.pokemon.data.data_source.local.room.PokemonRoomDatabaseProvider
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity
import com.example.pokemon.data.entity.PokemonListEntity
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonRoomDatabaseRepo @Inject constructor(@ApplicationContext applicationContext: Context) :
    PokemonListLocalRepo {

    private val pokemonDatabase = PokemonRoomDatabaseProvider.getDatabase(applicationContext)
    private val pokemonDao = pokemonDatabase.pokemonDao()

    override suspend fun insertAll(pokemons: List<PokemonListEntity>) = pokemonDao.insertAll(pokemons.map { it.toPokemonEntity() })
    override fun pagingSource(): PagingSource<Int, PokemonRoomEntity> = pokemonDao.pagingSource()
    override suspend fun clearAll() = pokemonDao.clearAll()
    override suspend fun updateImage(id: Int, image: String) = pokemonDao.updateImage(id, image)
    override suspend fun withTransaction(task: suspend () -> Unit) {
        pokemonDatabase.withTransaction {
            task()
        }
    }
}