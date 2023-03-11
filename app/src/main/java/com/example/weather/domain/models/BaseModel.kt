package com.example.weather.domain.models

data class BaseModel(
    val currentTemperature : String,
    val city: String,
    val lastTimeUpdate : String,
)
