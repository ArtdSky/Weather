package com.example.weather.domain.usecase

import com.example.weather.domain.models.LocationModel
import com.example.weather.domain.repository.Repository

class GetCurrentLocation(
    private val repository: Repository
) {
    suspend operator fun invoke() : LocationModel{
        return repository.getCurrentLocation()
    }
}