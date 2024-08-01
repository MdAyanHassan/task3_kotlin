package com.example.movies

import android.app.Application
import com.example.movies.di.AppContainer
import com.example.movies.di.DefaultAppContainer

class ItemsApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}