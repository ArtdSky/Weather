package com.example.weather.data.storage

import com.example.weather.data.storage.network.ApiConfig
import com.example.weather.data.storage.network.models.ResponseOpenWeathermap
import com.example.weather.data.storage.network.models.ResponseVisualCrossing
import com.example.weather.data.storage.network.models.ResponseWeatherApi

class ApiStorage() : Storage {

    override suspend fun getWeatherApiData(latlon: String): ResponseWeatherApi {
        ApiConfig.setBaseUrl("https://api.weatherapi.com/")
        return ApiConfig.retrofitApiService.getWeatherApiData(
            key = " a417b27b7c0746ceab5124157232501",
            q = latlon
        )
    }

    override suspend fun getOpenWeathermapData(lat : String, lon : String): ResponseOpenWeathermap {
        ApiConfig.setBaseUrl("https://api.openweathermap.org")
        return ApiConfig.retrofitApiService.getOpenWeathermapData(
            appid = "1a1aaae6a5881fb09024b0f1e1621e6e",
            units = "metric",
            lat = lat,
            lon = lon
        )
    }

    override suspend fun getVisualCrossingData(lat: String, lon: String): ResponseVisualCrossing {
        ApiConfig.setBaseUrl("https://weather.visualcrossing.com/")
        return ApiConfig.retrofitApiService.getVisualCrossingData(
            lat = lat,
            lon = lon,
            key = "NVB8CAXSGKZAQZTKEDDTXJPLC"
        )

    }


}