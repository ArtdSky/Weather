package com.example.weather.data.repository

import com.example.weather.data.storage.Storage
import com.example.weather.data.storage.network.models.ResponseWeatherApi
import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class RepositoryImpl(
    private val storage: Storage
) : Repository {


    override suspend fun getData(key: String, q: String): BaseModel {
        return mapDataToDomain(storage.getData(key = key, q = q))
    }


    private fun mapDataToDomain(data: ResponseWeatherApi): BaseModel {
        return BaseModel(
            currentTemperature = data.current.temp_c,
            city = data.location.name,
            lastTimeUpdate = data.current.last_updated
        )
    }
}