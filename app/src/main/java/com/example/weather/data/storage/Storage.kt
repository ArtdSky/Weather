package com.example.weather.data.storage

import com.example.weather.data.storage.network.models.ResponseWeatherApi
import com.example.weather.domain.models.BaseModel

interface Storage {

    suspend fun getData(key : String, q : String): ResponseWeatherApi

}