package com.example.movies.domain.repository

import com.example.movies.domain.model.Item
import com.example.movies.domain.model.Response
import kotlinx.coroutines.flow.Flow

interface ItemsRepository {

    suspend fun getItems(): Flow<Response<List<Item>>>

}