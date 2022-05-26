package com.example.pokemon.domain.usecases

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
    suspend fun invoke(id:Int) = withContext(Dispatchers.IO){remoteRepo.getPokemonDetails(id)}
}