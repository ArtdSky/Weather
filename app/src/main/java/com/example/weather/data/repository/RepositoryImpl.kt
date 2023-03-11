package com.example.weather.data.repository

import com.example.weather.data.storage.Storage
import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class RepositoryImpl(
    private val storage : Storage
) : Repository {


    override suspend fun getData(): List<BaseModel> {
        return mapDataToDomain( storage.getData() )
    }


    private fun mapDataToDomain( data : List<BaseModel> ): List<BaseModel> {
        return data
    }
}