package com.example.pokemon.presentation.recycler_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.pokemon.data.data_source.reomote.PokemonRetrofitAPI
import com.example.pokemon.data.repoImpl.PokemonDetailsRepoImpl
import com.example.pokemon.databinding.PokemonSmallCardBinding
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonDetailsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class PokemonPagingAdapter(val coroutineScope: CoroutineScope) :
    PagingDataAdapter<PokemonEntity, PokemonPagingAdapter.PokemonListViewHolder>(COMPARETOR) {
    class PokemonListViewHolder(val pokemonSmallCardBinding: PokemonSmallCardBinding) : RecyclerView.ViewHolder(pokemonSmallCardBinding.root)
    val pokemonDetailsRepo :PokemonDetailsRepo = PokemonDetailsRepoImpl(PokemonRetrofitAPI.getClient)
    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        val item = getItem(position)
        holder.pokemonSmallCardBinding.pokemaonName.text = item?.name.toString()
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        holder.pokemonSmallCardBinding.pokemonImage.setImageDrawable(null)
        coroutineScope.launch(Dispatchers.IO){
            val urlParts = item?.url?.split('/')
            val pokemonId = urlParts?.get(urlParts.lastIndex-1)?.toInt()?:1
            val pokemonDetails = pokemonDetailsRepo.getPokemonDetails(pokemonId)
            val pokemonImageUrl = pokemonDetails.sprites!!.other!!.home!!.front_default!!
            withContext(Dispatchers.Main){
                Glide
                    .with(holder.pokemonSmallCardBinding.root)
                    .load(pokemonImageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.pokemonSmallCardBinding.pokemonImage)
            }
        }
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