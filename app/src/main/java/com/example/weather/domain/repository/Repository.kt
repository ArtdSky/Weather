package com.example.weather.domain.repository

import com.example.weather.domain.models.LocationModel
import com.example.weather.domain.models.TemperatureModel

interface Repository {

    suspend fun getWeatherApiData(latlon: String) : TemperatureModel

    suspend fun getOpenWeathermapData(lat : String, lon : String) : TemperatureModel

    suspend fun getVisualCrossingData(lat : String, lon : String) : TemperatureModel

    suspend fun getCurrentLocation() : LocationModel
}