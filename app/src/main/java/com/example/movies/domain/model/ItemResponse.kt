package com.example.movies.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemResponse(
    val id: Int?,
    val name: String?,
    val type: String?,
    @SerialName("logo_100px")
    val logoUrl: String?,
    @SerialName("ios_appstore_url")
    val appStoreUrl: String?,
    @SerialName("android_playstore_url")
    val playStoreUrl: String?,
    @SerialName("android_scheme")
    val androidScheme: String?,
    @SerialName("ios_scheme")
    val iosScheme: String?,
    val regions: List<String>?
)
