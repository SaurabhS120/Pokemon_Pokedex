package com.example.pokemon.data.data_source.local.room.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokemon.data.data_source.local.room.entity.PokemonRoomEntity
import com.example.pokemon.domain.repos.PokemonListLocalRepo
import com.example.pokemon.domain.usecases.PokemonBase64UseCase
import com.example.pokemon.domain.usecases.PokemonListRemoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRoomRemoteMediator(
    private val localRepo: PokemonListLocalRepo,
    private val viewModelScope: CoroutineScope,
    private val pokemonListRemoteUseCase: PokemonListRemoteUseCase,
    private val pokemonBase64Usecase: PokemonBase64UseCase
) : RemoteMediator<Int, PokemonRoomEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonRoomEntity>
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
                    if ((state.anchorPosition ?: 0) > 1 && lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    } else if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = false
                        )
                    }
                    lastItem.id?.inc()
                }
            }

            // Suspending network load via Retrofit. This doesn't need to be
            // wrapped in a withContext(Dispatcher.IO) { ... } block since
            // Retrofit's Coroutine CallAdapter dispatches on a worker
            // thread.
            val response = pokemonListRemoteUseCase.invoke((loadKey?:1) / state.config.pageSize)

            localRepo.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localRepo.clearAll()
                }

                // Insert new users into database, which invalidates the
                // current PagingData, allowing Paging to present the updates
                // in the DB.
                response.results!!.map {
                    viewModelScope.async(Dispatchers.Default) {
                        var base64Image = it.imageBase64
                        if (base64Image == null || base64Image == "") {
                            base64Image = pokemonBase64Usecase.invoke(it.url)
                            it.id?.let { localRepo.updateImage(it, base64Image) }
                        }
                        it.imageBase64 = base64Image
                    }
                }.awaitAll()
                localRepo.insertAll(response.results)
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