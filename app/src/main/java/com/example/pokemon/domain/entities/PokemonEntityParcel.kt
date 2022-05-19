package com.example.pokemon.domain.entities

import android.os.Parcel
import android.os.Parcelable

class PokemonEntityParcel(
    id: Int?,
    name: String,
    url: String,
    imageBase64: String = ""
) : PokemonEntity(id, name, url, imageBase64), Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    constructor(pokemonEntity: PokemonEntity) : this(
        pokemonEntity.id,
        pokemonEntity.name,
        pokemonEntity.url,
        pokemonEntity.imageBase64 ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id ?: 0)
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(imageBase64)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonEntityParcel> {
        override fun createFromParcel(parcel: Parcel): PokemonEntityParcel {
            return PokemonEntityParcel(parcel)
        }

        override fun newArray(size: Int): Array<PokemonEntityParcel?> {
            return arrayOfNulls(size)
        }

    }
}