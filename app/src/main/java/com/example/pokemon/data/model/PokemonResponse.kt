package com.example.pokemon.data.model

import android.net.Uri
import com.example.pokemon.data.repoImpl.PokemonPageConfig

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

data class ResultsItem(
	val name: String? = null,
	val url: String? = null
)

