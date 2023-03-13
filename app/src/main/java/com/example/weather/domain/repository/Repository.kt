package com.example.weather.domain.repository

import com.example.weather.data.storage.local.models.WeatherEntity
import com.example.weather.domain.models.LocationModel
import com.example.weather.domain.models.TemperatureModel

interface Repository {

    suspend fun getWeatherApiData(location: String): TemperatureModel

    suspend fun getOpenWeathermapData(lat: String, lon: String): TemperatureModel

    suspend fun getVisualCrossingData(lat: String, lon: String): TemperatureModel

    suspend fun getCurrentLocation(): LocationModel

    suspend fun insertWeather(temperatureModel: TemperatureModel)

    suspend fun getAllWeather(): List<TemperatureModel>

    suspend fun getWeather(id: Int): TemperatureModel

    suspend fun updateWeather(weather: TemperatureModel)

    suspend fun clearWeatherTable()


}