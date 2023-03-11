package com.example.weather.data.storage.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {

    private const val BASE_URL =
        "https://api.weatherapi.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    var retrofitApiService: ApiService = retrofit.create(ApiService::class.java)
    private set

    fun setBaseUrl(newBaseUrl: String) {
        val newRetrofit = retrofit.newBuilder()
            .baseUrl(newBaseUrl)
            .build()
        retrofitApiService = newRetrofit.create(ApiService::class.java)
    }


}