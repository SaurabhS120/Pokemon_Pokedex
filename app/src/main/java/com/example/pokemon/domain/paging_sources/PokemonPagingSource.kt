package com.example.pokemon.domain.paging_sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemon.data.model.ResultsItem
import com.example.pokemon.domain.converter.PokemonPageUrlToNoConverter
import com.example.pokemon.domain.converter.PokemonResponseToUseCaseListConverter
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonRepo
import com.example.pokemon.domain.usecases.PokemonUseCase

class PokemonPagingSource(val pokemonRepo: PokemonRepo) : PagingSource<Int, PokemonEntity>() {
    var nextPageNumber:Int? = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonEntity> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = pokemonRepo.getPokemonList(nextPageNumber)
            this.nextPageNumber = response.next?.let {
                PokemonPageUrlToNoConverter.convert(it)
            }
            return LoadResult.Page(
                data = PokemonResponseToUseCaseListConverter.convert(response),
                prevKey = null, // Only paging forward.
                nextKey = response.next?.let { PokemonPageUrlToNoConverter.convert(it) } ?:-1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonEntity>): Int? {
        return nextPageNumber
    }

}