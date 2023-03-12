package com.example.weather.domain.usecase

import com.example.weather.domain.models.TemperatureModel
import com.example.weather.domain.repository.Repository

class InsertWeatherToDbUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(temperatureModel: TemperatureModel){
        return repository.insertWeather(temperatureModel = temperatureModel)
    }
}