package com.example.pokemon.presentation.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.databinding.PokemonSmallCardBinding
import com.example.pokemon.domain.converter.Base64ToByteArray
import com.example.pokemon.data.entity.PokemonListEntity
import java.util.*

class PokemonPagingAdapter :
    PagingDataAdapter<PokemonListEntity, PokemonPagingAdapter.PokemonListViewHolder>(COMPARATOR) {
    private var listener: ((pokemonListEntity: PokemonListEntity?) -> Unit)? = {}
    fun setOnClickListener(listener: (pokemonListEntity: PokemonListEntity?) -> Unit) {
        this.listener = listener
    }

    class PokemonListViewHolder(val pokemonSmallCardBinding: PokemonSmallCardBinding) :
        RecyclerView.ViewHolder(pokemonSmallCardBinding.root)

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val item = getItem(position)
        holder.pokemonSmallCardBinding.pokemaonName.text = item?.name.toString()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        holder.pokemonSmallCardBinding.pokemonImage.setImageDrawable(null)
        val base64Image = item?.imageBase64
        val byteArray = base64Image?.let { Base64ToByteArray.convert(it) }
        holder.pokemonSmallCardBinding.root.setOnClickListener {
            listener?.let { it1 ->
                it1(item)
            }
        }
        Glide
            .with(holder.pokemonSmallCardBinding.root)
            .load(byteArray)
            .into(holder.pokemonSmallCardBinding.pokemonImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        val pokemonSmallCardView =
            PokemonSmallCardBinding.inflate(LayoutInflater.from(parent.context))
        return PokemonListViewHolder(pokemonSmallCardView)
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<PokemonListEntity>() {
            override fun areItemsTheSame(oldItem: PokemonListEntity, newItem: PokemonListEntity): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: PokemonListEntity,
                newItem: PokemonListEntity
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }

}