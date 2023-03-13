package com.example.weather.domain.usecase

import com.example.weather.domain.models.TemperatureModel
import com.example.weather.domain.repository.Repository

class GetWeatherApiDataUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(location: String): TemperatureModel {
        return repository.getWeatherApiData(location = location)
    }

}