package com.example.weather.domain.usecase

import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class GetOpenWeathermapDataUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(lat : String, lon : String): BaseModel {
        return repository.getOpenWeathermapData(lat = lat, lon = lon)
    }

}