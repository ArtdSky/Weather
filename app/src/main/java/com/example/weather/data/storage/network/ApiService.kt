package com.example.weather.data.storage.network

import com.example.weather.data.storage.network.models.ResponseOpenWeathermap
import com.example.weather.data.storage.network.models.ResponseVisualCrossing
import com.example.weather.data.storage.network.models.ResponseWeatherApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/v1/current.json")
    suspend fun getWeatherApiData(
        @Query("key") key: String,
        @Query("q") q: String
    ): ResponseWeatherApi

    @GET("/data/2.5/weather")
    suspend fun getOpenWeathermapData(
        @Query("appid") appid: String,
        @Query("units") units: String,
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): ResponseOpenWeathermap

    @GET("/VisualCrossingWebServices/rest/services/timeline/{lat},{lon}")
    suspend fun getVisualCrossingData(
        @Path("lat") lat: String,
        @Path("lon") lon: String,
        @Query("key") key: String,
    ): ResponseVisualCrossing


}