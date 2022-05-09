package com.example.pokemon.data.data_source

import com.example.pokemon.data.repoImpl.PokemonPageConfig

data class PageDetails(val offset:Int,val pageSize:Int = PokemonPageConfig.PAGE_SIZE)