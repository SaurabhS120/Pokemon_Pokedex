package com.example.pokemon.presentation.ui.detailsFragments

import android.os.Bundle

interface PokemonDetailFragment<T> {
    interface ArgumentHelper<T>{
        fun createBundle(obj:T):Bundle
    }
    fun argumentHelper():ArgumentHelper<T>


}