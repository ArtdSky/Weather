package com.example.weather.domain.usecase

import com.example.weather.domain.repository.Repository

class ClearDbUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(){
        return repository.clearWeatherTable()
    }
}