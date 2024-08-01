package com.example.movies.di

import com.example.movies.data.network.ItemsApi
import com.example.movies.data.repository.ItemsRepoImpl
import com.example.movies.domain.repository.ItemsRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultAppContainer : AppContainer {

    private val baseUrl = "https://api.watchmode.com/v1/"

    private val itemsApiService = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
        .create(ItemsApi::class.java)

    override val itemsRepository: ItemsRepository by lazy {
        ItemsRepoImpl(
            apiService = itemsApiService
        )
    }

}