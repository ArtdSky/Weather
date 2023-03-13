package com.example.weather.data.storage

import com.example.weather.data.storage.local.models.WeatherEntity
import com.example.weather.data.storage.network.models.ResponseCurrentLocation
import com.example.weather.data.storage.network.models.ResponseOpenWeathermap
import com.example.weather.data.storage.network.models.ResponseVisualCrossing
import com.example.weather.data.storage.network.models.ResponseWeatherApi

interface Storage {

    suspend fun getWeatherApiData(location: String): ResponseWeatherApi

    suspend fun getOpenWeathermapData(lat: String, lon: String): ResponseOpenWeathermap

    suspend fun getVisualCrossingData(lat: String, lon: String): ResponseVisualCrossing

    suspend fun getCurrentLocation(): ResponseCurrentLocation


    suspend fun insertWeather(weatherEntity: WeatherEntity)

    suspend fun getAllWeather(): List<WeatherEntity>

    suspend fun getWeather(id: Int): WeatherEntity

    suspend fun updateWeather(weather: WeatherEntity)

    suspend fun clearWeatherTable()

}