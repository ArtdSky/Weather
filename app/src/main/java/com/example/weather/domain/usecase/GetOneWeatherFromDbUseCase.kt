package com.example.weather.domain.usecase

import com.example.weather.domain.models.TemperatureModel
import com.example.weather.domain.repository.Repository

class GetOneWeatherFromDbUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(id : Int): TemperatureModel {
        return repository.getWeather(id = id)
    }
}