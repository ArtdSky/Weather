package com.example.weather.domain.usecase

import com.example.weather.domain.models.TemperatureModel
import com.example.weather.domain.repository.Repository

class GetAllWeatherFromDbUsecase(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<TemperatureModel> {
        return repository.getAllWeather()
    }
}