package com.example.pokemon.domain.entities

import android.os.Parcel
import android.os.Parcelable

open class PokemonListEntity(
    var id: Int? = null,
    val name: String,
    val url: String,
    var imageBase64: String? = ""):Parcelable {

    init {
        if (id==null) id = getId()
    }

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    private fun getId(): Int {
        val urlParts = url.split('/')
        return urlParts.get(urlParts.lastIndex - 1).toInt()
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id ?: 0)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(imageBase64)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonListEntity> {
        override fun createFromParcel(parcel: Parcel): PokemonListEntity {
            return PokemonListEntity(parcel)
        }

        override fun newArray(size: Int): Array<PokemonListEntity?> {
            return arrayOfNulls(size)
        }

    }
}
