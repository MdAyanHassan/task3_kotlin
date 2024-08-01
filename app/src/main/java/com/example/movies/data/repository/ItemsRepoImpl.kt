package com.example.movies.data.repository

import android.util.Log
import com.example.movies.data.network.ItemsApi
import com.example.movies.domain.mappers.toItem
import com.example.movies.domain.model.Item
import com.example.movies.domain.model.Response
import com.example.movies.domain.repository.ItemsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class ItemsRepoImpl(
    private val apiService: ItemsApi
): ItemsRepository {
    override suspend fun getItems(): Flow<Response<List<Item>>> {
        return callbackFlow {
            val response = try {
                val items = apiService.getItems()
                    .map { itemResponse ->
                        itemResponse.toItem()
                    }
                Response.Success(items)
            } catch (e: Exception) {
                Log.e("MyApp", e.message.toString())
                Response.Error(e)
            }
            trySend(response)

            awaitClose {
                close()
            }
        }
    }

}