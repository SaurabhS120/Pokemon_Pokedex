package com.example.pokemon.data.model

import android.net.Uri
import com.example.pokemon.data.config.PokemonPageConfig
import com.example.pokemon.domain.usecases.PokemonUseCase

data class PokemonResponse(
	val next: String? = null,
	val previous: String? = null,
	val count: Int? = null,
	val results: List<ResultsItem>? = null
){
	fun getNextPageNo():Int{
		val nextPageUrl = Uri.parse(next)
		val nextPageNo = Integer.parseInt(nextPageUrl.getQueryParameter("offset").toString()) / PokemonPageConfig.PAGE_SIZE
		return nextPageNo
	}
}

class ResultsItem(
	name: String,
	url: String
):PokemonUseCase(name,url){
	fun getId():Int{
		val urlParts = url?.split('/')
		val pokemonId = urlParts?.get(urlParts.lastIndex-1)?.toInt()?:1
		return pokemonId
	}
	init {
	    super.id = getId()
	}
}

