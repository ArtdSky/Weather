package com.example.weather.domain.repository

import com.example.weather.domain.models.BaseModel

interface Repository {

    suspend fun getData(key : String, q : String) : BaseModel
}