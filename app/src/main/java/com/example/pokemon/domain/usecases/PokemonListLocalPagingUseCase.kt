package com.example.pokemon.domain.usecases

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.example.pokemon.domain.entities.PokemonListEntity
import com.example.pokemon.domain.repos.PokemonListPagingMediator
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonListLocalPagingUseCase @Inject constructor(
    private val pokemonListPagingMediator: PokemonListPagingMediator
    ) {
    fun  invoke(): LiveData<PagingData<PokemonListEntity>> = pokemonListPagingMediator.getPokemons()
}