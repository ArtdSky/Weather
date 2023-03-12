package com.example.weather.data.storage.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseOpenWeathermap(
    @Json(name = "name")
    val name: String,
    @Json(name = "dt")
    val dt: String,
    @Json(name = "main")
    val main: Main
)

@JsonClass(generateAdapter = true)
data class Main(
    @Json(name = "temp")
    val temp: String

)