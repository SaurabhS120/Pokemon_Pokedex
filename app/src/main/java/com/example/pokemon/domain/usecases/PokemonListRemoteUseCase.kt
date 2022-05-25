package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonListRemoteUseCase @Inject constructor(private val remoteRepo: PokemonRemoteRepo) {
    suspend fun invoke(pageNo:Int) = remoteRepo.getPokemonList(pageNo)
}