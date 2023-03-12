package com.example.weather.data.storage

import com.example.weather.data.storage.network.ApiConfig
import com.example.weather.data.storage.network.models.ResponseWeatherApi

class ApiStorage() : Storage {

    override suspend fun getData(key: String, q: String): ResponseWeatherApi {
        return ApiConfig.retrofitApiService.getData(
            key = key,
            q = q
        )
    }

    


}