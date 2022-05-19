package com.example.pokemon.domain.converter

import android.util.Base64

object Base64ToByteArray : Converter<String, ByteArray> {
    override fun convert(obj: String): ByteArray {
        return Base64.decode(obj, Base64.DEFAULT)
    }
}