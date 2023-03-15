package com.example.weather.data.storage.local

import com.example.weather.data.storage.local.models.WeatherEntity

class WeatherRepository(
    private val weatherDao: WeatherDao
) {

    suspend fun insertWeather(weatherEntity: WeatherEntity) {
        return weatherDao.insertWeather(weatherEntity)
    }

    suspend fun getAllWeather(): List<WeatherEntity> {
        return weatherDao.getAllWeather()
    }

    suspend fun getWeather(id: Int): WeatherEntity {
        return weatherDao.getWeather(id)
    }

    suspend fun updateWeather(weather: WeatherEntity){
        return weatherDao.updateWeather(weather = weather)
    }
    suspend fun clearWeatherTable(){
        return weatherDao.clearWeatherTable()
    }
}

