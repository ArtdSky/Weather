package com.example.weather.domain.usecase

import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class GetDataUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(key: String, q: String): BaseModel {
        return repository.getData(key = key, q = q)
    }

}