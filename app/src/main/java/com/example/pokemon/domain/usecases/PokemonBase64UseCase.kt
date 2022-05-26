package com.example.pokemon.domain.usecases

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.pokemon.domain.converter.PngToBase64
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class PokemonBase64UseCase @Inject constructor(val pokemonDetailsUseCase: PokemonDetailsUseCase) {
    var result = ""
    suspend fun invoke(url:String):String{
        return withContext(Dispatchers.Default) {
            val urlParts = url.split('/')
            val pokemonId = urlParts.get(urlParts.lastIndex - 1).toInt()
            val pokemonDetails = pokemonDetailsUseCase.invoke(pokemonId)
            val pokemonImageUrl = pokemonDetails.pokemonImagePath
            val bm = BitmapFactory.decodeStream(URL(pokemonImageUrl).openStream())
            val smallBitmap = Bitmap.createScaledBitmap(bm, 200, 200, true)
            PngToBase64.convert(smallBitmap)
        }
    }
}