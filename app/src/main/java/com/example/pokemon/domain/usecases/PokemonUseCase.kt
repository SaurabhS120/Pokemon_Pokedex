package com.example.pokemon.domain.usecases

import com.example.pokemon.domain.entities.PokemonEntity

open class PokemonUseCase(name: String,url:String,id:Int?=null) : PokemonEntity(id,name,url)