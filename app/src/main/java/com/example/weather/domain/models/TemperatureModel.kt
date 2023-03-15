package com.example.weather.domain.models

data class TemperatureModel(
    val id : Int? = null,
    val currentTemperature : String,
    val city: String,
    val lastTimeUpdate : String,
)
