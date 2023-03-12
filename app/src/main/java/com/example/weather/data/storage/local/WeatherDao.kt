package com.example.weather.data.storage.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.data.storage.local.models.WeatherEntity

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather_table")
    suspend fun getAllWeather(): List<WeatherEntity>

    @Query("SELECT * FROM weather_table WHERE id = :id")
    suspend fun getWeather(id: Int): WeatherEntity
}