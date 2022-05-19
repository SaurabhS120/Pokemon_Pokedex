package com.example.pokemon.domain.converter

import android.net.Uri
import com.example.pokemon.data.config.PokemonPageConfig

object PokemonPageUrlToNoConverter : Converter<String, Int> {
    override fun convert(obj: String): Int {
        val url = Uri.parse(obj)
        return url.getQueryParameter("offset").toString().toInt() / PokemonPageConfig.PAGE_SIZE
    }
}