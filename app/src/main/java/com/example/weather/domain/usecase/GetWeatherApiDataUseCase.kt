package com.example.weather.domain.usecase

import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class GetWeatherApiDataUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(latlon: String): BaseModel {
        return repository.getWeatherApiData(latlon = latlon)
    }

}