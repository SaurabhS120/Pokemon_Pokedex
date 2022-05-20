package com.example.pokemon.domain.usecases

import com.example.pokemon.data.repoImpl.paging.mediator.PokemonListPagingRepoImpl
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.repos.PokemonListPagingRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonListLocalPagingUseCase @Inject constructor(
    private val localRepo: PokemonListLocalRepo,
    private val pokemonListRemoteUseCase: PokemonListRemoteUseCase,
    private val pokemonBase64Usecase: PokemonBase64UseCase
    ) {
    fun  call(viewModelScope:CoroutineScope):PokemonListPagingRepo = PokemonListPagingRepoImpl(localRepo,viewModelScope,pokemonListRemoteUseCase,pokemonBase64Usecase)
}