package com.example.weather.domain.usecase

import com.example.weather.domain.models.TemperatureModel
import com.example.weather.domain.repository.Repository
import com.example.weather.presentation.models.WeatherData

class InsertWeatherToDbUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(data: WeatherData) {
        val temperatureModel: TemperatureModel = TemperatureModel(
            id = data.id,
            currentTemperature = data.currentTemperature ?: "",
            city = data.city ?: "",
            lastTimeUpdate = data.lastTimeUpdate ?: ""
        )
        return repository.insertWeather(temperatureModel = temperatureModel)
    }
}