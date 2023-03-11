package com.example.weather.data.storage.network

import com.example.weather.data.storage.network.models.ResponseWeatherApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/current.json")
    suspend fun getData(
        @Query("key") key: String,
        @Query("q") q: String
    ): ResponseWeatherApi
}