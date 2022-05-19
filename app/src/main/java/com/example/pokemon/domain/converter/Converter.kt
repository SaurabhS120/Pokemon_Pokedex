package com.example.pokemon.domain.converter

interface Converter<A, B> {
    fun convert(obj: A): B
}