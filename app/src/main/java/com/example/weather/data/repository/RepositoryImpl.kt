package com.example.weather.data.repository

import android.util.Log
import com.example.weather.data.storage.Storage
import com.example.weather.data.storage.network.models.ResponseOpenWeathermap
import com.example.weather.data.storage.network.models.ResponseVisualCrossing
import com.example.weather.data.storage.network.models.ResponseWeatherApi
import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class RepositoryImpl(
    private val storage: Storage
) : Repository {


    override suspend fun getWeatherApiData(latlon: String): BaseModel {
        return mapDataToDomain(storage.getWeatherApiData(latlon = latlon))
    }

    override suspend fun getOpenWeathermapData(lat: String, lon: String): BaseModel {
        return mapDataToDomain(storage.getOpenWeathermapData(lat = lat, lon = lon))
    }

    override suspend fun getVisualCrossingData(lat: String, lon: String): BaseModel {
        return mapDataToDomain(storage.getVisualCrossingData(lat = lat, lon = lon))
    }

    private fun mapDataToDomain(data: Any): BaseModel {
        return when (data) {
            is ResponseWeatherApi -> BaseModel(
                currentTemperature = data.current.temp_c,
                city = data.location.name,
                lastTimeUpdate = data.current.last_updated_epoch
            )
            is ResponseVisualCrossing -> {
                val tempC = fahrenheitToCelsius(data.currentConditions.temp)
                BaseModel(
                    currentTemperature = tempC,
                    city = data.timezone,
                    lastTimeUpdate = data.currentConditions.datetimeEpoch
                )
            }
            is ResponseOpenWeathermap -> BaseModel(
                currentTemperature = data.main.temp,
                city = data.name,
                lastTimeUpdate = data.dt
            )
            else -> {
                Log.d("TAG-RepositoryImpl", "Unknown response type: ${data.toString()}")
                BaseModel(
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

}