package com.example.movies.domain.mappers

import com.example.movies.domain.model.Item
import com.example.movies.domain.model.ItemResponse

fun ItemResponse.toItem(): Item {
    return Item(
        name = this.name,
        type = this.type,
        logoUrl = this.logoUrl,
        appStoreUrl = this.appStoreUrl,
        googleStoreUrl = this.playStoreUrl
    )
}