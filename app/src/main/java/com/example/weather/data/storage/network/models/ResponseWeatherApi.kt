package com.example.weather.data.storage.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseWeatherApi(
	@Json(name = "location")
	val location: Location,
	@Json(name = "current")
	val current: Current

)

@JsonClass(generateAdapter = true)
data class Current(
	@Json(name = "temp_c")
	val temp_c: String,
	@Json(name = "last_updated_epoch")
	val last_updated_epoch: String,
)

@JsonClass(generateAdapter = true)
data class Location(
	@Json(name = "name")
	val name: String
)



