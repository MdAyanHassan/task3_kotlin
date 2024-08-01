package com.example.movies.data.network

import com.example.movies.domain.model.ItemResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ItemsApi {

    @GET("sources/?apiKey=e0ukp9nF8P1LbZdvLpisAJ1HMaa5nYXqovxcf67P")
    suspend fun getItems(): List<ItemResponse>

}