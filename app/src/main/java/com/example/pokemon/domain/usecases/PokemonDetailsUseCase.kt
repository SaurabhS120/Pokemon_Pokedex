package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.entities.PokemonDetailsEntity
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDetailsUseCase @Inject constructor(private val remoteRepo: PokemonRemoteRepo) {
    private val pokemonDetailsCache = mutableMapOf<Int,PokemonDetailsEntity>()
    @Singleton
    suspend fun invoke(id:Int,useCache:Boolean = false): PokemonDetailsEntity {
        var  pokemonDetailsEntity = PokemonDetailsEntity.createEmptyObject()
        withContext(Dispatchers.Default){
            if (useCache && pokemonDetailsCache.containsKey(id)){
                pokemonDetailsEntity = pokemonDetailsCache.get(id)?:pokemonDetailsEntity
            }else{
                withContext(Dispatchers.IO){
                    pokemonDetailsEntity = remoteRepo.getPokemonDetails(id)
                    if (useCache) {
                        pokemonDetailsCache.put(id,pokemonDetailsEntity)
                    }
                }
            }
        }
        return pokemonDetailsEntity
    }
}