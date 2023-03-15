package com.example.weather.data.storage.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseCurrentLocation(
    @Json(name = "city")
    val city: String,
    @Json(name = "latitude")
    val latitude: String,
    @Json(name = "longitude")
    val longitude: String,
    )
