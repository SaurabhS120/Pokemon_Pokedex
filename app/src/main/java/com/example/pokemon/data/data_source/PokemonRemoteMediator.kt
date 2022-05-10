package com.example.pokemon.data.data_source

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pokemon.data.data_source.local.PokemonDatabase
import com.example.pokemon.domain.converter.PngToBase64
import com.example.pokemon.domain.entities.PokemonEntity
import com.example.pokemon.domain.repos.PokemonDetailsRepo
import com.example.pokemon.domain.repos.PokemonRepo
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException
import java.net.URL

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val database: PokemonDatabase,
    private val networkService: PokemonRepo,
    private val pokemonDetailsRepo: PokemonDetailsRepo,
    private val viewModelScope: CoroutineScope,
) : RemoteMediator<Int, PokemonEntity>() {
    val pokemonDao = database.pokemonDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            // The network load method takes an optional after=<user.id>
            // parameter. For every page after the first, pass the last user
            // ID to let it continue from where it left off. For REFRESH,
            // pass null to load the first page.
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                // In this example, you never need to prepend, since REFRESH
                // will always load the first page in the list. Immediately
                // return, reporting end of pagination.
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()

                    // You must explicitly check if the last item is null when
                    // appending, since passing null to networkService is only
                    // valid for initial load. If lastItem is null it means no
                    // items were loaded after the initial REFRESH and there are
                    // no more items to load.
                    if (state.anchorPosition?:0>1 && lastItem==null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    else if(lastItem==null){
                        return MediatorResult.Success(
                            endOfPaginationReached = false
                        )
                    }
                    lastItem?.id
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = networkService.getPokemonList(((loadKey?:0)+1)/state.config.pageSize)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDao.clearAll()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                response.results!!.map {
                    viewModelScope.async(Dispatchers.IO) {
                        var base64Image = it.imageBase64
                        if (base64Image==null||base64Image==""){
                            val urlParts = it?.url?.split('/')
                            val pokemonId = urlParts?.get(urlParts.lastIndex-1)?.toInt()?:1
                            val pokemonDetails = pokemonDetailsRepo.getPokemonDetails(pokemonId)
                            val pokemonImageUrl = pokemonDetails.sprites!!.other!!.home!!.front_default!!
                            val bm = BitmapFactory.decodeStream(URL(pokemonImageUrl).openStream())
                            val smallBitmap = Bitmap.createScaledBitmap(bm,200,200,true)
                            base64Image = PngToBase64.convert(smallBitmap)
                            it?.id?.let { database.pokemonDao().updateImage(it,base64Image) }
                        }
                        it.imageBase64 = base64Image
                    }
                }.awaitAll()
                response.results!!.let {
                    pokemonDao.insertAll(it)
                }
            }

            MediatorResult.Success(
                endOfPaginationReached = response.next == null
            )
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}