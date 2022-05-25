package com.example.pokemon.data.repoImpl.remote

import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.data.data_source.reomote.retrofit.PokemonRetrofitApiProvider
import com.example.pokemon.domain.entities.PokemonDetailsEntity
import com.example.pokemon.data.data_source.reomote.retrofit.response.PokemonRetrofitResponse
import com.example.pokemon.domain.repos.PokemonRemoteRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonRetrofitRepo @Inject constructor() : PokemonRemoteRepo {

    private val retrofitApi = PokemonRetrofitApiProvider.getRetrofitApi()

    override suspend fun getPokemonList(pageNo: Int): PokemonRetrofitResponse =
        retrofitApi.getPokemonList(PokemonPageConfig.PAGE_SIZE * pageNo)

    override suspend fun getPokemonDetails(id: Int): PokemonDetailsEntity =
        retrofitApi.getPokemonDetails(id).toPokemonDetailsResponse()

}