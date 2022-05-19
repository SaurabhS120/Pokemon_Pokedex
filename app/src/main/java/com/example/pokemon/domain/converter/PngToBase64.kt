package com.example.pokemon.domain.converter

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

object PngToBase64 : Converter<Bitmap, String> {
    override fun convert(obj: Bitmap): String {
        val baos = ByteArrayOutputStream()
        obj.compress(Bitmap.CompressFormat.PNG, 100, baos) // bm is the bitmap object
        val byteArrayImage: ByteArray = baos.toByteArray()
        return Base64.encodeToString(byteArrayImage, Base64.DEFAULT)
    }
}