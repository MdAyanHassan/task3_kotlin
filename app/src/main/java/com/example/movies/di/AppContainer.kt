package com.example.movies.di

import com.example.movies.domain.repository.ItemsRepository

interface AppContainer {
    val itemsRepository: ItemsRepository
}