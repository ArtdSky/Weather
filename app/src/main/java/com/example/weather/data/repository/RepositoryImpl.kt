package com.example.weather.data.repository

import android.util.Log
import com.example.weather.data.storage.Storage
import com.example.weather.data.storage.network.models.ResponseCurrentLocation
import com.example.weather.data.storage.network.models.ResponseOpenWeathermap
import com.example.weather.data.storage.network.models.ResponseVisualCrossing
import com.example.weather.data.storage.network.models.ResponseWeatherApi
import com.example.weather.domain.models.LocationModel
import com.example.weather.domain.models.TemperatureModel
import com.example.weather.domain.repository.Repository

class RepositoryImpl(
    private val storage: Storage
) : Repository {


    override suspend fun getWeatherApiData(latlon: String): TemperatureModel {
        return mapDataToDomain(storage.getWeatherApiData(latlon = latlon))
    }

    override suspend fun getOpenWeathermapData(lat: String, lon: String): TemperatureModel {
        return mapDataToDomain(storage.getOpenWeathermapData(lat = lat, lon = lon))
    }

    override suspend fun getVisualCrossingData(lat: String, lon: String): TemperatureModel {
        return mapDataToDomain(storage.getVisualCrossingData(lat = lat, lon = lon))
    }

    override suspend fun getCurrentLocation(): LocationModel {
        return mapLocationToDomain(storage.getCurrentLocation() )
    }

    private fun mapDataToDomain(data: Any): TemperatureModel {
        return when (data) {
            is ResponseWeatherApi -> TemperatureModel(
                currentTemperature = data.current.temp_c,
                city = data.location.name,
                lastTimeUpdate = data.current.last_updated_epoch
            )
            is ResponseVisualCrossing -> {
                val tempC = fahrenheitToCelsius(data.currentConditions.temp)
                TemperatureModel(
                    currentTemperature = tempC,
                    city = data.timezone,
                    lastTimeUpdate = data.currentConditions.datetimeEpoch
                )
            }
            is ResponseOpenWeathermap -> TemperatureModel(
                currentTemperature = data.main.temp,
                city = data.name,
                lastTimeUpdate = data.dt
            )
            else -> {
                Log.d("TAG-RepositoryImpl", "Unknown response type: ${data.toString()}")
                TemperatureModel(
                    currentTemperature = "",
                    city = "",
                    lastTimeUpdate = ""
                )
            }
        }
    }

    private fun fahrenheitToCelsius(input: String): String {
        return input.toDoubleOrNull()?.let { (it - 32) * 5 / 9 }?.let { "%.1f".format(it) } ?: ""
    }

    private fun mapLocationToDomain(data : ResponseCurrentLocation) : LocationModel {
        return LocationModel(
            latitude = data.latitude,
            longitude = data.longitude
        )
    }

}