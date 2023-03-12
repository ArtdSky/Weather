package com.example.weather.data.storage.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ResponseVisualCrossing(
    @Json(name = "currentConditions")
    val currentConditions: CurrentConditions,
    @Json(name = "timezone")
    val timezone: String,
)


@JsonClass(generateAdapter = true)
data class CurrentConditions(
    @Json(name = "datetimeEpoch")
    val datetimeEpoch: String,
    @Json(name = "temp")
    val temp: String,
)