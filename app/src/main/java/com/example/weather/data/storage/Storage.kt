package com.example.weather.data.storage

import com.example.weather.domain.models.BaseModel

interface Storage {

    suspend fun getData(): List<BaseModel>

}