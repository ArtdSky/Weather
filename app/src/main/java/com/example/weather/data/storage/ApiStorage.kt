package com.example.weather.data.storage

import com.example.weather.domain.models.BaseModel

class ApiStorage() : Storage{

    override suspend fun getData(): List<BaseModel> {
        TODO("Not yet implemented")
    }
    
}