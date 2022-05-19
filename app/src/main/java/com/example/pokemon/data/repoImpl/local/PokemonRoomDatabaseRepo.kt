package com.example.pokemon.data.repoImpl.local

import android.content.Context
import androidx.paging.PagingSource
import androidx.room.withTransaction
import com.example.pokemon.data.data_source.local.room.PokemonRoomDatabaseProvider
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonRoomDatabaseRepo @Inject constructor(@ApplicationContext applicationContext:Context) :
    PokemonListLocalRepo {
    val pokemonDatabase = PokemonRoomDatabaseProvider.getDatabase(applicationContext)
    val pokemonDao = pokemonDatabase.pokemonDao()
    override suspend fun insertAll(pokemons: List<PokemonEntity>) = pokemonDao.insertAll(pokemons)

    override fun pagingSource(): PagingSource<Int, PokemonEntity> = pokemonDao.pagingSource()

    override suspend fun clearAll() = pokemonDao.clearAll()

    override suspend fun updateImage(id: Int, image: String) = pokemonDao.updateImage(id,image)
    override suspend fun withTransaction(task:suspend () -> Unit) {
        pokemonDatabase.withTransaction {
            task()
        }
    }
}