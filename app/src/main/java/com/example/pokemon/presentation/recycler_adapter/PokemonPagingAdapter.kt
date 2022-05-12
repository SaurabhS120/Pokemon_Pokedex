package com.example.pokemon.presentation.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.databinding.PokemonSmallCardBinding
import com.example.pokemon.domain.converter.Base64ToByteArray
import com.example.pokemon.domain.entities.PokemonEntity
import java.util.*

class PokemonPagingAdapter :
    PagingDataAdapter<PokemonEntity, PokemonPagingAdapter.PokemonListViewHolder>(COMPARETOR) {

    class PokemonListViewHolder(val pokemonSmallCardBinding: PokemonSmallCardBinding) : RecyclerView.ViewHolder(pokemonSmallCardBinding.root)
    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val item = getItem(position)
        holder.pokemonSmallCardBinding.pokemaonName.text = item?.name.toString()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        holder.pokemonSmallCardBinding.pokemonImage.setImageDrawable(null)
        val base64Image=item?.imageBase64
        val byteArray = base64Image?.let { Base64ToByteArray.convert(it) }

        Glide
            .with(holder.pokemonSmallCardBinding.root)
            .load(byteArray)
            .into(holder.pokemonSmallCardBinding.pokemonImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val pokemonSmallCardView = PokemonSmallCardBinding.inflate(LayoutInflater.from(parent.context))
        return PokemonListViewHolder(pokemonSmallCardView)
    }
    companion object{
        val COMPARETOR = object : DiffUtil.ItemCallback<PokemonEntity>(){
            override fun areItemsTheSame(oldItem: PokemonEntity, newItem: PokemonEntity): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonEntity,
                newItem: PokemonEntity
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

}