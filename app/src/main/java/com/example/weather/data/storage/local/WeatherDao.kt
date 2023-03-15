package com.example.weather.data.storage.local

import androidx.room.*
import com.example.weather.data.storage.local.models.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    suspend fun getAllWeather(): List<WeatherEntity>

    @Query("SELECT * FROM weather_table WHERE id = :id")
    suspend fun getWeather(id: Int): WeatherEntity

    @Update
    suspend fun updateWeather(weather: WeatherEntity)

    @Query("DELETE FROM weather_table")
    suspend fun clearWeatherTable()
}