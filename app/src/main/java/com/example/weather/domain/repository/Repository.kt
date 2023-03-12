package com.example.weather.domain.repository

import com.example.weather.domain.models.BaseModel

interface Repository {

    suspend fun getWeatherApiData(latlon: String) : BaseModel

    suspend fun getOpenWeathermapData(lat : String, lon : String) : BaseModel

    suspend fun getVisualCrossingData(lat : String, lon : String) : BaseModel
}