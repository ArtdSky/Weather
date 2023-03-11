package com.example.weather.domain.usecase

import com.example.weather.domain.models.BaseModel
import com.example.weather.domain.repository.Repository

class GetDataUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(key: String, q: String): BaseModel {
        return repository.getData(key = key, q = q)
//        key = "a417b27b7c0746ceab5124157232501",
//        q = "54.99,73.37"
    }

}